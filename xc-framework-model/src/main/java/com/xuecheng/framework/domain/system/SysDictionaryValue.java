package com.xuecheng.framework.domain.system;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 字典数据
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
public class SysDictionaryValue {
    /**
     * 项目id
     */
    @Field("sd_id")
    private String sdId;
    /**
     * 项目名称
     */
    @Field("sd_name")
    private String sdName;
    /**
     * 项目状态（1：可用，0不可用）
     */
    @Field("sd_status")
    private String sdStatus;

}
