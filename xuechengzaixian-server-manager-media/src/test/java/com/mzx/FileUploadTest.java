package com.mzx;

import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.course.TeachPlanMedia;
import com.mzx.media.SpringApplicationServerMediaApp;
import com.mzx.media.dao.ITeachPlanMediaDao;
import com.mzx.media.service.IMediaUploadService;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * @author ZhenXinMa
 * @date 2020/5/12 21:20
 */
@SpringBootTest(classes = {SpringApplicationServerMediaApp.class})
@RunWith(SpringRunner.class)
@Slf4j
public class FileUploadTest {

    @Resource
    private IMediaUploadService service;

    /**
     * 文件分块.
     *
     * @throws Exception
     */
    @Test
    public void t1() throws Exception {

        // 要分块的文件.
        File sourceFile = new File("D:/Server/FFmpeg/test/lucene.mp4");
        log.info("开始对  {}  进行分块 ", sourceFile.getName());
        // 分块路径.
        String chunkPath = "D:/Server/FFmpeg/test/chunk/";
        // 分块目录.
        File chunkFolder = new File(chunkPath);
        if (!chunkFolder.exists()) {

            chunkFolder.mkdirs();
        }

        // 分块大小  一块为1M. 1KB = 1024 B 1MB = 1024KB
        // 数据存储是以10进制表示，数据传输是以2进制表示.
        long chunkSize = 1024 * 1024 * 1;
        // 分块数量
        long chunkNum = (long) Math.ceil(sourceFile.length() * 1.0 / chunkSize);
        if (chunkNum <= 0) {

            chunkNum = 1;
        }

        // Byte单位是一个字节.
        // 缓冲区大小  1KB.  每次从原始文件中读取的数据是该字节数组大小.
        byte[] b = new byte[1024];
        // 使用RandomAccessFile访问文件.
        RandomAccessFile random_read = new RandomAccessFile(sourceFile, "r");
        // 分块: 效率最高的是开启10个线程进行分块.
        log.info("开始进行分块");
        for (int i = 0; i < chunkNum; i++) {

            // 创建分块文件
            File file = new File(chunkPath + i);
            boolean newFile = false;
            if (!file.exists()) {

                // File.exists()  存在返回true.
                newFile = file.createNewFile();
            } else {
                newFile = true;
            }

            if (newFile) {

                // 向分块文件中写入数据.
                RandomAccessFile random_write = new RandomAccessFile(file, "rw");
                int len = -1;
                // 每次从源文件中读取 1KB 大小的文件  如果读到文件末尾 那么就就返回-1.
                while ((len = random_read.read(b)) != -1) {

                    // 从0开始 每次写入len个字节. 当一个分块文件中存储的数据 < 1M 的时候就循环读写.
                    /*
                     *RandomAccessFile.write() : 参数含义
                     *  b : 要写入的数据.
                     *  off :  数据中的起始偏移量.<从字节数组中向文件中写入的起始偏移量.>
                     *  len :  要写入的字节数.
                     */
                    random_write.write(b, 0, len);
                    if (file.length() > chunkSize) {

                        // 结束整个循环.
                        break;
                    }

                }

                random_write.close();
            }

        }

        random_read.close();
        log.info("分块结束.");
    }

    @Test
    public void t2() throws Exception {

        System.out.println("开始对文件进行合并");
        // 先获取块文件目录.
        File file = new File("D:/Server/FFmpeg/test/chunk/");
        // 合并文件.
        File mergeFile = new File("D:/Server/FFmpeg/test/lucene1.mp4");
        if (mergeFile.exists()) {

            // 如果存在则删除.
            mergeFile.delete();
        }

        mergeFile.createNewFile();
        RandomAccessFile random_Write = new RandomAccessFile(mergeFile, "rw");
        // 指针指向文件顶端.
        random_Write.seek(0);
        // 声明 1K 的缓冲区.
        byte[] b = new byte[1024];
        // 分块列表. 获取到一个文件里面的所有文件.
        File[] files = file.listFiles();
        // 转成集合: 方便排序.
        List<File> fileList = new ArrayList<>(Arrays.asList(files));
        /*
          对于文件列表需要排列的花,对于文件需要实现Comparator接口来确定排序规则.
         */
        Collections.sort(fileList, new Comparator<File>() {

            @Override
            public int compare(File o1, File o2) {

                if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {

                    return -1;
                }

                return 1;
            }

        });

        // 只要进行了Collections.sort(List list,xx) 方法那么list已经是排好序的了.
        for (File file1 : fileList) {

            RandomAccessFile random_read = new RandomAccessFile(file1, "rw");
            int len = -1;
            // 如果到文件末尾那么就会返回-1
            while ((len = random_read.read(b)) != -1) {

                random_Write.write(b, 0, len);
            }

            random_read.close();
        }

        random_Write.close();
        System.out.println("文件合并完成.");
    }

    @Test
    public void t3(){

        System.out.println("哈哈哈哈哈哈啊哈");
        CoursePub pub = new CoursePub();
        CoursePub pub1 = pub;
        System.out.println(pub.equals(pub1));   //true
        System.out.println(1);

    }

    @Test
    public void t4(){
        service.register("","",0L,"","");
        System.out.println(1);
    }




}
