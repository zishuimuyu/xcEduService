package com.xuecheng.framework.domain.system;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 数据字典
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
@Document(collection = "sys_dictionary")
public class SysDictionary {

    @Id
    private String id;
    /**
     * 字典名称
     */
    @Field("d_name")
    private String dName;
    /**
     * 字典分类
     */
    @Field("d_type")
    private String dType;
    /**
     * 字典数据
     */
    @Field("d_value")
    private List<SysDictionaryValue> dValue;

}
