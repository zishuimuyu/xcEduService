package com.xuecheng.framework.domain.media;

import com.xuecheng.framework.utils.MD5Util;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
@Document(collection = "media_video_course")
public class MediaVideoCourse {

    @Id
    private String id;
    /**
     * 课程id
     */
    private String courseid;
    /**
     * 章节id
     */
    private String chapter;
    /**
     * 文件id
     */
    private String fileId;
    /**
     * 视频处理方式
     */
    private String processType;
    /**
     * 视频处理状态
     */
    private String processStatus;
    /**
     * HLS处理结果
     */
    private String hls_m3u8;
    private List<String> hls_ts_list;

    public MediaVideoCourse(String fileId,String courseid,String chapter){
        this.fileId = fileId;
        this.courseid = courseid;
        this.chapter = chapter;
        this.id = MD5Util.getStringMD5(courseid+chapter);
        /**
         * 生成 hls
         */
        this.processType = "302002";
        /**
         * 未处理
         */
        this.processStatus="303001";
    }

}
