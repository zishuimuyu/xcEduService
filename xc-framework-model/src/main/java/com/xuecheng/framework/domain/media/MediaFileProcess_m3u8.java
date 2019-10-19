package com.xuecheng.framework.domain.media;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
public class MediaFileProcess_m3u8 extends MediaFileProcess {

    /**
     * ts列表
     */
    private List<String> tslist;

}
