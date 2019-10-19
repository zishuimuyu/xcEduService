package com.xuecheng.manage_cms.dao;

import com.xuecheng.manage_cms.service.CmsPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 *
 * @author：GJH
 * @createDate：2019/10/6
 * @company：洪荒宇宙加力蹲大学
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PageServiceTest {

    @Autowired
    CmsPageService cmsPpageService;

    @Test
    public void testGetPageHtml(){
        String pageHtml = cmsPpageService.getPageHtml("5b9b5c2fb6eb080aa0b28e56");
        System.out.println(pageHtml);

    }

}
