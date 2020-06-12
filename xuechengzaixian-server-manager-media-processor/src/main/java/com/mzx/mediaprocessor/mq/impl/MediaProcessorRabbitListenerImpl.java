package com.mzx.mediaprocessor.mq.impl;

import com.alibaba.fastjson.JSON;
import com.mzx.framework.model.media.MediaFile;
import com.mzx.framework.model.media.MediaFileProcess_m3u8;
import com.mzx.mediaprocessor.callback.RabbitReturnCallBack;
import com.mzx.mediaprocessor.dao.MediaFileRepository;
import com.mzx.mediaprocessor.mq.MediaProcessorRabbitListener;
import com.mzx.util.HlsVideoUtil;
import com.mzx.util.Mp4VideoUtil;
import com.mzx.util.StringAppendUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author ZhenXinMa
 * @date 2020/5/18 17:37
 */
@Slf4j
@Component
public class MediaProcessorRabbitListenerImpl implements MediaProcessorRabbitListener {

    @Value(value = "${xuechengzaixian.video.ffmpeg-path}")
    private String ffmpegPath = "";

    /**
     * D:/Server/FFmpeg/ffmpeg/test/
     */
    @Value(value = "${xuechengzaixian.video.location}")
    private String serverPath = "";

    @Resource
    private MediaFileRepository mediaFileRepository;


    @Override
    @Async
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "${xuechengzaixian.mq.queue‐media‐video‐processor}", durable = "true"),
                    exchange = @Exchange(value = "${xuechengzaixian.mq.exchange}", durable = "true", type = "direct"),
                    key = "${xuechengzaixian.mq.routingkey‐media‐video}"
            )
    )
    @RabbitHandler
    public void listener(@Payload String message, Channel channel, @Headers Map<String, Object> headers) {
        /*监听指定MQ队列中的消息
         * message是一个Map结构
         * 该Map我认为应该包含两个信息
         * 一个是MediaID
         * 一个是上传该Media的Teacher信息.*/
        System.out.println("消费端监听到消息: " + message);
        System.out.println(Thread.currentThread().getName());

        Map messageMap = JSON.parseObject(message, Map.class);
        // 获取到要将视屏转码的MediaID.
        String mediaID = (String) messageMap.get("mediaID");
        // 从MongoDB中找出想应的文件. mediaID 一定不为空, 因为那个是在生产消息的经过确认而传输而来.
        Optional<MediaFile> optional = mediaFileRepository.findById(mediaID);
        if (!optional.isPresent()) {

            // ...
            return;
        }

        MediaFile mediaFile = optional.get();
        // 目前只支持avi文件 后续可以继续开发.
        if (mediaFile.getFileType() == null || !"avi".equals(mediaFile.getFileType())) {

            // 更新无需处理.
            mediaFile.setFileStatus("303004");
            mediaFileRepository.save(mediaFile);
            return;
        } else {

            mediaFile.setFileStatus("303001");
            // 这里也还用放入数据库中吗？
            mediaFileRepository.save(mediaFile);
        }

        // D:/Server/FFmpeg/test/x/a/
        String videoPath = StringAppendUtils.appendString(new StringBuilder(), serverPath, "/",
                mediaFile.getFilePath(), mediaFile.getFileName());
        String mp4_name = StringAppendUtils.appendString(new StringBuilder(), mediaFile.getFileId(), ".mp4");
        String mp4_folderPath = StringAppendUtils.appendString(new StringBuilder(), serverPath, "/",
                mediaFile.getFilePath());
        Mp4VideoUtil mp4VideoUtil = new Mp4VideoUtil(ffmpegPath, videoPath, mp4_name, mp4_folderPath);
        String result = mp4VideoUtil.generateMp4();
        log.info("MP4打印日志。。。。。");
        System.out.println(result);

        /*-------------------MP4转换为M3u8文件.------------*/
        // MP4Path:  D:\Server\FFmpeg\test\5\f\5fbb79a2016c0eb609ecd0cd3dc48016\xxx.mp4
        String video_path = StringAppendUtils.appendString(new StringBuilder(), serverPath, "/",
                mediaFile.getFilePath(), mediaFile.getFileId(), ".mp4");
        // hls与chunk同一目录
        String m3u8Folder = serverPath + "/" + mediaFile.getFilePath() + "hls/";
        String m3u8Name = mediaFile.getFileId() + ".m3u8";
        List<String> ts_list = this.mp4ToM3u8(ffmpegPath, video_path, m3u8Name, m3u8Folder);
        mediaFile.setProcessStatus("303002");
        MediaFileProcess_m3u8 mediaFileProcess_m3u8 = new MediaFileProcess_m3u8();
        mediaFileProcess_m3u8.setTslist(ts_list);
        mediaFile.setMediaFileProcess_m3u8(mediaFileProcess_m3u8);
        // 保存m3u8的URL地址.
        String url = serverPath + "/" + mediaFile.getFilePath() + "hls/" + mediaFile.getFileId() + ".m3u8";
        mediaFile.setFileUrl(url);
        mediaFileRepository.save(mediaFile);
        // 手工签收
        Long o = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {

            channel.basicAck(o, false);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private List<String> mp4ToM3u8(String ffmpegPath, String videoPath, String m3u8Name, String m3u8Path) {

        HlsVideoUtil hlsVideoUtil = new HlsVideoUtil(ffmpegPath, videoPath, m3u8Name, m3u8Path);
        hlsVideoUtil.generateM3u8();
        List<String> ts_list = hlsVideoUtil.get_ts_list();
        return ts_list;
    }

}
