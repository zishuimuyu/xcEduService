package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * CmsPage页面预览前端控制器
 *
 * @author：GJH
 * @createDate：2019/9/23
 * @company：洪荒宇宙加力蹲大学
 */
@Controller
public class CmsPagePreviewController extends BaseController {
    @Autowired
    CmsPageService cmsPageService;

    /**
     * 页面预览
     * @param pageId 需要预览的页面的ID
     */
    @RequestMapping(value = "/cms/preview/{pageId}", method = RequestMethod.GET)
    public void preview(@PathVariable("pageId") String pageId) {
        //静态化,获取页面内容
        String pageHtml = cmsPageService.getPageHtml(pageId);
        //通过request发送请求,将内容输出,
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(pageHtml.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
