package com.mzx.media.service.impl;

import com.mzx.common.exception.ThrowException;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.media.MediaFile;
import com.mzx.framework.model.media.response.CheckChunkResult;
import com.mzx.framework.model.media.response.MediaCode;
import com.mzx.media.dao.IMediaFileRepository;
import com.mzx.media.mq.MediaRabbitMessageProducer;
import com.mzx.media.service.IMediaUploadService;
import com.mzx.util.StringAppendUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * @author ZhenXinMa
 * @date 2020/5/17 18:10
 */
@Slf4j
@Service
public class MediaUploadServiceImpl implements IMediaUploadService {

    @Resource
    private IMediaFileRepository repository;

    @Resource
    private MediaRabbitMessageProducer mediaRabbitMessageProducer;

    @Value(value = "${xuechengzaixian-server-meida.upload.location}")
    private String upload_path = "";

    @Override
    public ResponseResult register(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {

        /*文件注册也就是看看本地文件是否存在、以及MongoDB中该文件是否存在,MongoDB文件是以视屏的MD5作为ID存储的.*/
        // 获取到要存储的路径.
        String fileRealPath = this.getFileAbsolutePath(fileMd5, fileExt);
        File file = new File(fileRealPath);
        if (file.exists()) {

            // file 存在.
            ThrowException.exception(MediaCode.UPLOAD_FILE_REGISTER_EXIST);
        }

        Optional<MediaFile> optional = repository.findById(fileMd5);
        if (optional.isPresent()) {

            MediaFile mediaFile = optional.get();
            if (mediaFile != null) {

                ThrowException.exception(MediaCode.UPLOAD_FILE_REGISTER_EXIST);
            }
            //?
        }

        // 如果不存在那么就创建
        // 应该先看看路径是否存在.
        boolean b = this.createFileMkdir(fileMd5);
        if (!b) {

            // 创建失败(目前硬盘中没有此目录.)
            ThrowException.exception(MediaCode.UPLOAD_FILE_REGISTER_FAIL);
        }

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public CheckChunkResult checkChunk(String fileMd5, Integer chunk, Integer chunkSize) {

//        String filePath = this.getFileMkdir(fileMd5);

        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        boolean b = this.createFileMkdir(chunkFileFolderPath);
        if (b) {
            // 在本地上有文件.
            return new CheckChunkResult(MediaCode.CHUNK_FILE_EXIST_CHECK, false);
        }

        return new CheckChunkResult(MediaCode.CHUNK_FILE_EXIST_CHECK, true);
    }

    @Override
    public ResponseResult uploadChunk(MultipartFile file, String fileMd5, Integer chunk) {

        if (file == null) {

            ThrowException.exception(MediaCode.UPLOAD_FILE_REGISTER_FAIL);
        }

        String fileFolderPath = this.getChunkFileFolderPath(fileMd5);
        File chunkFolder = new File(fileFolderPath);
        if (!chunkFolder.exists()) {

            chunkFolder.mkdirs();
        }

        // 上传文件前的所有准备工作已经准备齐全.
        String s = StringAppendUtils.appendString(new StringBuilder(), fileFolderPath, chunk.toString());
        File chunkFile = new File(s);
        System.out.println("文件名字:   " + chunkFile.getName());
        if (chunkFile.exists()) {

            try {

                // 创建的是文件(不带后缀) 用于保存分块的文件.
                chunkFile.delete();
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
        try {

            chunkFile.createNewFile();
            System.out.println("创建分块文件成功。");
        } catch (IOException e) {

            e.printStackTrace();
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {

            inputStream = file.getInputStream();
            outputStream = new FileOutputStream(chunkFile);
            IOUtils.copy(inputStream, outputStream);
            System.out.println(1);
            System.out.println("文件复制成功.");
        } catch (IOException e) {

            // 抛出异常.
            ThrowException.exception(MediaCode.CHUNK_FILE_UPLOAD_FALL);
            e.printStackTrace();
        } finally {

            try {

                inputStream.close();
                outputStream.close();
            } catch (IOException e) {

                log.info("关闭IO流出现异常: {}", e.getMessage());
                e.printStackTrace();
            }
        }

        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public ResponseResult mergeChunks(String fileMd5, String fileName, Long fileSize, String mimetype, String fileExt) {

        /*合并分块. 先获取该资源目录.*/
        String chunkFileFolderPath = this.getChunkFileFolderPath(fileMd5);
        File chunkFile = new File(chunkFileFolderPath);
        // 这里用创建文件目录吗？ 我认为不用.
        List<File> chunkFileList = this.getChunkFileList(chunkFile);
        // 合并文件路径 如果存在则删除.
        File mergeFile = new File(this.getFilePath(fileMd5, fileExt));
        if (mergeFile.exists()) {

            mergeFile.delete();
        }

        try {

            mergeFile.createNewFile();
        } catch (IOException e) {

            // 如果创建文件失败这里应该是要抛出异常的.
            ThrowException.exception(MediaCode.MERGE_FILE_FAIL);
            e.printStackTrace();
        }

        // 合并文件返回的file是已经有了具体内容的文件.
        // 这里我认为应该是可以开启线程进行文件合并的.
        mergeFile = mergeFile(mergeFile, chunkFileList);
        if (mergeFile == null) {

            // 合并文件失败.
            ThrowException.exception(MediaCode.MERGE_FILE_FAIL);
            // 抛出异常后程序停止执行.
        }

        /*向MediaProcessor发送消息进行视频转换.*/
        Map<String, Object> message = new HashMap<>();
        Map<String, Object> headers = new HashMap<>();
        message.put("id", "");
        message.put("mediaID", fileMd5);
        mediaRabbitMessageProducer.sendMessage(message, headers, fileMd5);

        // 校验MD5文件.
        /*
        下面的暂时没有完成.  明天继续.  开工.
       MD5校验这一部分有异常.
         */
//        boolean checkMd5 = this.checkMd5(mergeFile, fileMd5);
//        if (!checkMd5) {
//
//            ThrowException.exception(MediaCode.MERGE_FILE_CHECKFAIL);
//        }

        // 向数据库保存信息.
        boolean b = this.addMediaFileToMongodb(fileMd5, fileName, fileSize, mimetype, fileExt);
        if (b) {

            return new ResponseResult(CommonCode.SUCCESS);
        }

        return new ResponseResult(CommonCode.SERVER_ERROR);
    }

    /**
     * 返回例子: D:/xxx/xxx/0/a
     *
     * @param fileMd5
     * @param fileExt
     * @return
     */
    private String getFileRealPath(String fileMd5, String fileExt) {

        // 获取到一级目录. substring(a,b):返回一个字符串，该字符串是此字符串的子字符串。 子字符串以指定'索引'处的字符开头，并扩展到该字符串的末尾。
        String one = fileMd5.substring(0, 1);
        // 获取到二级目录.
        String two = fileMd5.substring(1, 2);
        // 三级目录是MD5值.
        String path = StringAppendUtils.appendString(new StringBuilder(), upload_path, one, "/", two, "/",
                fileMd5, "/", fileMd5, ".", fileExt);
        return path;
    }

    /**
     * 返回例子: /0/a/md5/md5.fileExt
     * 返回的是文件上传的相对路径.
     *
     * @param fileMd5
     * @param fileExt
     * @return
     */
    private String getFileRelativePath(String fileMd5, String fileExt) {

        /*不包含根目录.*/
        String one = fileMd5.substring(0, 1);
        String two = fileMd5.substring(1, 2);
        String path = StringAppendUtils.appendString(new StringBuilder(), "/", one, "/", two, "/", fileMd5, "/",
                fileMd5, ".", fileExt);
        return path;

    }

    /**
     * 返回类型: D:/xxx/xxx/0/a/md5/
     *
     * @param fileMd5
     * @return
     */
    private String getFileMkdir(String fileMd5) {

        /*返回要上传的文件目录.
         * getFileFolderPath==*/
        String one = fileMd5.substring(0, 1);
        String two = fileMd5.substring(1, 2);
        String fileMkdir = StringAppendUtils.appendString(new StringBuilder(), upload_path, one, "/", two, "/",
                fileMd5, "/");

        return fileMkdir;
    }

    /**
     * 返回例子:  D:/xxx/xxx/0/a/md5/md5.mp4
     *
     * @param fileMd5
     * @param fileExt
     * @return
     */
    private String getFileAbsolutePath(String fileMd5, String fileExt) {

        String fileMkdir = this.getFileMkdir(fileMd5);
        fileMkdir = fileMkdir + fileMd5 + "." + fileExt;
        return fileMkdir;
    }

    private String getFilePath(String fileMd5, String fileExt) {

        String one = fileMd5.substring(0, 1);
        String two = fileMd5.substring(1, 2);
        return StringAppendUtils.appendString(new StringBuilder(), upload_path, one, "/", two, "/", fileMd5,
                "/", fileMd5, ".", fileExt);

    }

    private boolean createFileMkdir(String fileMd5) {

        /*只要在本地有指定路径上的文件夹,那么就返回true(可以没有但是我成功的创建了),
         * 如果在本地上没有指定路径的文件夹那么且创建失败那么就返回false*/
        String fileMkdir = this.getFileMkdir(fileMd5);
        File file = new File(fileMd5);
        if (file.exists()) {

            return true;
        }
        // 创建成功返回true 创建失败返回false;
        boolean b = file.mkdirs();
        return b;
    }

    /**
     * 返回: D:/xxx/xxx/0/a/md5/chunks/
     *
     * @param fileMd5
     * @return
     */
    private String getChunkFileFolderPath(String fileMd5) {

        String path = this.getFileMkdir(fileMd5);
        String chunkPath = StringAppendUtils.appendString(new StringBuilder(), path, "chunks/");
        return chunkPath;

    }

    private List<File> getChunkFileList(File chunkFileFolder) {

        /* 这里返回的是一个有序的数组,其是按 数字从低到高排列.*/
        File[] files = chunkFileFolder.listFiles();
        // 需要将数组转换为ArrayList
        List<File> fileList = Arrays.asList(files);
        Collections.sort(fileList, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {

                if (Integer.parseInt(o1.getName()) > Integer.parseInt(o2.getName())) {

                    return 1;
                }

                return -1;
            }

        });

        return fileList;
    }

    private File mergeFile(File targetFile, List<File> chunkFileList) {

        /*
         * 第一个参数： 空的文件(需要将合并的文件输入到该空文件中)
         * 第二个参数：分块文件列表
         * 改进：这里是不是可以开启一个线程进行文件合并：先假设返回的合并文件成功 如果在线程结束后发现合并文件失败那么用户可以自己进行点击按钮的
         *      方式进行继续上传。 我认为这将会很大提升效率.
         * 返回值： 返回的是已经合并完之后的输出文件(该文件能正常使用).*/
        try {

            RandomAccessFile random_write = new RandomAccessFile(targetFile, "rw");
            // 读取文件缓存.  1M
            byte[] b = new byte[1024];
            for (File file : chunkFileList) {

                RandomAccessFile random_read = new RandomAccessFile(file, "r");
                int len = -1;
                while ((len = random_read.read(b)) != -1) {
                    // != -1则一直读取. 直到读取到当前文件末尾才返回-1;
                    random_write.write(b, 0, len);
                }

                random_read.close();
            }

            random_write.close();
        } catch (Exception e) {
            log.error("文件合并出现错误：：；；");
            System.out.println(e);
        } finally {

        }

        return targetFile;
    }

    private boolean checkMd5(File mergeFile, String fileMd5) {

        if (mergeFile == null || StringUtils.isEmpty(fileMd5)) {

            return false;
        }

        try {

            FileInputStream inputStream = new FileInputStream(mergeFile);
            String md5DigestAsHex = DigestUtils.md5DigestAsHex(inputStream);
            if (fileMd5.equals(md5DigestAsHex)) {

                return true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    private boolean addMediaFileToMongodb(String fileMd5, String fileName, Long fileSize,
                                          String mimetype, String fileExt) {

        MediaFile mediaFile = new MediaFile();
        mediaFile.setFileId(fileMd5);
        String name = StringAppendUtils.appendString(new StringBuilder(), fileMd5, ".", fileExt);
        mediaFile.setFileName(name);
        mediaFile.setFileOriginalName(fileName);
        // 相对路径.
        mediaFile.setFilePath(this.getFileFolderRelativePath(fileMd5, fileExt));
        mediaFile.setFileSize(fileSize);
        // 301002上传成功.
        mediaFile.setFileStatus("301002");
        // 该文件的类型(后缀.)
        mediaFile.setFileType(fileExt);
        mediaFile.setMimeType(mimetype);

        // 保存.
        try {

            repository.save(mediaFile);
            return true;
        } catch (Exception e) {

            System.out.println(e);
            return false;
        }

    }

    /**
     * 返回例子：0/a/md5/
     *
     * @param fileMd5
     * @param fileExt
     * @return
     */
    private String getFileFolderRelativePath(String fileMd5, String fileExt) {

        String one = fileMd5.substring(0, 1);
        String two = fileMd5.substring(1, 2);
        /*URL统一前面不加'/'后面加'/'*/
        String path = StringAppendUtils.appendString(new StringBuilder(), one, "/", two, "/", fileMd5, "/");
        return path;
    }

}
