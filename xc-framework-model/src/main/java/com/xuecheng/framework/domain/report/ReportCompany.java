package com.xuecheng.framework.domain.report;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
@Document(collection = "report_company")
public class ReportCompany {

    @Id
    private String id;
    /**
     * 评价分数（平均分）
     */
    private Float evaluation_score;
    /**
     * 好评率
     */
    private Float good_scale;
    /**
     * 课程数
     */
    private Long course_num;
    /**
     * 学生人数
     */
    private Long student_num;
    /**
     * 播放次数
     */
    private Long play_num;
    

}
