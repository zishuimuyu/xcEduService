package com.xuecheng.framework.domain.report;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
@Document(collection = "report_course")
public class ReportCourse {

    @Id
    private String id;
    /**
     * 评价分数
     */
    private Float evaluation_score;
    /**
     * 收藏次数
     */
    private Long collect_num;
    /**
     * 播放次数
     */
    private Long play_num;
    /**
     * 学生人数
     */
    private Long student_num;
    /**
     * 课程时长
     */
    private Long timelength;

}
