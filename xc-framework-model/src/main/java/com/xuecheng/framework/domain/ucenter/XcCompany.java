package com.xuecheng.framework.domain.ucenter;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="xc_company")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class XcCompany implements Serializable {
    private static final long serialVersionUID = -916357110051689786L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String name;
    private String logo;
    private String intro;
    private String email;
    private String mobile;
    private String linkname;
    private String identitypic;
    private String worktype;
    private String businesspic;
    private String status;


}
