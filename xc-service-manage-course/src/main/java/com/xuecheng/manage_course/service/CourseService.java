package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.domain.cms.response.CmsPostPageResult;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.CoursePic;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CourseView;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.response.CourseCode;
import com.xuecheng.framework.domain.course.response.CoursePublishResult;
import com.xuecheng.framework.domain.course.response.CourseResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.utils.StringUtil;
import com.xuecheng.manage_course.client.CmsPageClient;
import com.xuecheng.manage_course.dao.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * 课程服务类
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Service
public class CourseService {

    @Autowired
    TeachplanMapper teachplanMapper;

    @Autowired
    CourseBaseRepository courseBaseRepository;

    @Autowired
    TeachplanRepository teachplanRepository;
    @Autowired
    CourseMarketRepository courseMarketRepository;
    @Autowired
    CoursePicRepository coursePicRepository;
    @Autowired
    CmsPageClient cmsPageClient;
    //从appication.yml配置文件中读取内容
    /**
     * 页面预的数据模型url
     */
    @Value("${course‐publish.dataUrlPre}")
    private String publish_dataUrlPre;
    @Value("${course‐publish.pagePhysicalPath}")
    private String publish_page_physicalpath;
    @Value("${course‐publish.pageWebPath}")
    private String publish_page_webpath;
    @Value("${course‐publish.siteId}")
    private String publish_siteId;
    @Value("${course‐publish.templateId}")
    private String publish_templateId;
    @Value("${course‐publish.previewUrl}")
    private String previewUrl;

    /**
     * 查询课程计划
     *
     * @param courseId 课程id
     * @return
     */
    public TeachplanNode findTeachplanList(String courseId) {
        TeachplanNode teachplanNode = teachplanMapper.selectList(courseId);
        return teachplanNode;
    }

    /**
     * 添加课程计划
     *
     * @param teachplan
     * @return
     */
    @Transactional
    public ResponseResult addTeachplan(Teachplan teachplan) {
        if (teachplan == null ||
                StringUtil.isEmpty(teachplan.getCourseid()) ||
                StringUtil.isEmpty(teachplan.getPname())) {
            ExceptionCast.cast(CommonCode.PARAM_NOT_COMPLETE);
        }
        //拿到课程id
        String courseid = teachplan.getCourseid();
        //拿到父级节点id
        String parentid = teachplan.getParentid();
        if (StringUtil.isEmpty(parentid)) {
            //查找根节点
            parentid = this.getTeachplanRoot(courseid);
        }
        //取出父级节点
        Optional<Teachplan> optional = teachplanRepository.findById(parentid);
        Teachplan parentNode = optional.get();
        //拿到父级节点级别
        String parentGrade = parentNode.getGrade();
        Teachplan teachplanNew = new Teachplan();
        //将页面提交过来的课程计划相关信息拷贝进新创建的课程计划中
        BeanUtils.copyProperties(teachplan, teachplanNew);
        teachplanNew.setParentid(parentid);
        teachplanNew.setCourseid(courseid);
        teachplanNew.setGrade(String.valueOf(Integer.parseInt(parentGrade) + 1));
        teachplanRepository.save(teachplanNew);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 获取课程计划
     *
     * @param courseId 课程id
     * @return
     */
    private String getTeachplanRoot(String courseId) {
        Optional<CourseBase> optional = courseBaseRepository.findById(courseId);
        if (!optional.isPresent()) {
            return null;
        }
        //拿到课程信息
        CourseBase courseBase = optional.get();
        List<Teachplan> teachplanList = teachplanRepository.findTeachplanByCourseidAndParentid(courseId, "0");
        if (CollectionUtils.isEmpty(teachplanList)) {
            //查询不到,这自动添加根节点
            Teachplan teachplan = new Teachplan();
            teachplan.setParentid("0");
            teachplan.setGrade("1");
            teachplan.setPname(courseBase.getName());
            teachplan.setCourseid(courseId);
            teachplan.setStatus("0");
            Teachplan savedTeachplan = teachplanRepository.save(teachplan);
            return savedTeachplan.getId();
        }
        return teachplanList.get(0).getId();
    }

    /**
     * 添加课程提交
     *
     * @param courseBase
     * @return
     */
    public CourseResult addCourseBase(CourseBase courseBase) {
        //课程状态为未发布
        courseBase.setStatus("202001");
        courseBaseRepository.save(courseBase);
        return new CourseResult(CommonCode.SUCCESS, courseBase.getId());
    }

    /**
     * 根据id获取课程基本信息
     *
     * @param courseId 课程id
     * @return
     */
    public CourseBase getCoursebaseById(String courseId) {
        Optional<CourseBase> courseBaseOptional = courseBaseRepository.findById(courseId);
        if (courseBaseOptional.isPresent()) {
            return courseBaseOptional.get();
        }
        ExceptionCast.cast(CourseCode.COURSE_GET_NOTEXISTS);
        return null;
    }


    /**
     * 更新课程基础信息
     *
     * @param courseId   课程id
     * @param courseBase
     * @return
     */
    public ResponseResult updateCoursebase(String courseId, CourseBase courseBase) {
        CourseBase one = this.getCoursebaseById(courseId);
        if (one == null) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        //修改课程信息
        one.setName(courseBase.getName());
        one.setMt(courseBase.getMt());
        one.setSt(courseBase.getSt());
        one.setGrade(courseBase.getGrade());
        one.setStudymodel(courseBase.getStudymodel());
        one.setUsers(courseBase.getUsers());
        one.setDescription(courseBase.getDescription());
        CourseBase save = courseBaseRepository.save(one);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 根据id查询课程营销信息
     *
     * @param courseId 课程id
     * @return
     */
    public CourseMarket getCourseMarketById(String courseId) {
        Optional<CourseMarket> optional = courseMarketRepository.findById(courseId);
        if (!optional.isPresent()) {
            return optional.get();
        }
        return null;
    }


    /**
     * 更新课程营销信息
     *
     * @param id           课程id
     * @param courseMarket
     * @return
     */
    @Transactional
    public CourseMarket updateCourseMarket(String id, CourseMarket courseMarket) {
        CourseMarket one = this.getCourseMarketById(id);
        if (one != null) {
            //课程信息已存在
            one.setCharge(courseMarket.getCharge());
            //课程有效期，开始时间
            one.setStartTime(courseMarket.getStartTime());
            //课程有效期，结束时间
            one.setEndTime(courseMarket.getEndTime());
            one.setPrice(courseMarket.getPrice());
            one.setQq(courseMarket.getQq());
            one.setValid(courseMarket.getValid());
            one = courseMarketRepository.save(one);
        } else {
            //课程营销信息不存在,新增一个
            //添加课程营销信息
            one = new CourseMarket();
            BeanUtils.copyProperties(courseMarket, one);
            //设置课程id
            one.setId(id);
            one = courseMarketRepository.save(one);
        }
        return one;
    }

    /**
     * 添加课程图片
     *
     * @param courseId 课程id
     * @param pic      图片路径
     * @return
     */
    @Transactional
    public ResponseResult saveCoursePic(String courseId, String pic) {
        CoursePic coursePic = null;
        //查询课程的图片信息是否已存在
        Optional<CoursePic> picOptional = coursePicRepository.findById(courseId);
        if (picOptional.isPresent()) {
            ///存在
            coursePic = picOptional.get();
        }
        if (coursePic == null) {
            //不存在
            coursePic = new CoursePic();
            coursePic.setCourseid(courseId);
        }
        coursePic.setPic(pic);
        CoursePic save = coursePicRepository.save(coursePic);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * 查询课程图片
     *
     * @param courseId 课程id
     * @return
     */
    public CoursePic findCoursePic(String courseId) {
        Optional<CoursePic> picOptional = coursePicRepository.findById(courseId);
        if (picOptional.isPresent()) {
            CoursePic coursePic = picOptional.get();
            return coursePic;
        }
        return null;
    }

    /**
     * 删除课程图片
     *
     * @param courseId 课程id
     * @return
     */
    @Transactional
    public ResponseResult deleteCoursePic(String courseId) {
        long result = coursePicRepository.deleteByCourseid(courseId);
        if (result > 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    /**
     * 查询课程视图信息,包括课程基本信息,图片,营销信息,课程计划
     *
     * @param courseId 课程id
     * @return
     */
    public CourseView getCourseView(String courseId) {
        CourseView courseView = new CourseView();
        //查询课程基本信息
        Optional<CourseBase> courseBaseOptional = courseBaseRepository.findById(courseId);
        if (courseBaseOptional.isPresent()) {
            CourseBase courseBase = courseBaseOptional.get();
            courseView.setCourseBase(courseBase);
        }
        //查询课程图片
        Optional<CoursePic> coursePicOptional = coursePicRepository.findById(courseId);
        if (coursePicOptional.isPresent()) {
            CoursePic coursePic = coursePicOptional.get();
            courseView.setCoursePic(coursePic);
        }
        //查询课程营销信息
        Optional<CourseMarket> courseMarketOptional = courseMarketRepository.findById(courseId);
        if (courseMarketOptional.isPresent()) {
            CourseMarket courseMarket = courseMarketOptional.get();
            courseView.setCourseMarket(courseMarket);
        }
        //查询课程计划
        TeachplanNode teachplanNode = teachplanMapper.selectList(courseId);
        courseView.setTeachplanNode(teachplanNode);
        return courseView;
    }

    /**
     * 课程预览
     *
     * @param courseId 课程id
     * @return
     */
    public CoursePublishResult preview(String courseId) {
        CourseBase courseBase = this.getCoursebaseById(courseId);
        if (courseBase == null) {
            ExceptionCast.cast(CourseCode.COURSE_GET_NOTEXISTS);
        }
        //准备cmsPage信息
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId(publish_siteId);
        cmsPage.setDataUrl(publish_dataUrlPre + courseId);
        cmsPage.setPageName(courseId + ".html");
        cmsPage.setPageAliase(courseBase.getName());
        cmsPage.setPagePhysicalPath(publish_page_physicalpath);
        cmsPage.setPageWebPath(publish_page_webpath);
        cmsPage.setTemplateId(publish_templateId);
        //1.请求cms添加页面
        //1.1远程调用cms服务的添加页面的方法
        CmsPageResult cmsPageResult = cmsPageClient.saveCmsPage(cmsPage);
        if (!cmsPageResult.isSuccess()) {
            //如果添加失败
            return new CoursePublishResult(CommonCode.FAIL, null);
        }
        //返回保存成功的页面
        CmsPage cmsPage1 = cmsPageResult.getCmsPage();
        //获取页面id
        String pageId = cmsPage1.getPageId();

        //2.拼装页面预览的URL
        String url = previewUrl + pageId;
        //3.返回CoursePublishResult,也就是返回页面预览的URL,
        return new CoursePublishResult(CommonCode.SUCCESS, url);
    }

    /**
     * 发布课程
     *
     * @param courseId 课程id
     * @return
     */
    @Transactional
    public CoursePublishResult publish(String courseId) {
        //1.准备cmsPage信息
        CourseBase courseBase = this.getCoursebaseById(courseId);
        if (courseBase == null) {
            ExceptionCast.cast(CourseCode.COURSE_GET_NOTEXISTS);
        }
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId(publish_siteId);
        cmsPage.setDataUrl(publish_dataUrlPre + courseId);
        cmsPage.setPageName(courseId + ".html");
        cmsPage.setPageAliase(courseBase.getName());
        cmsPage.setPagePhysicalPath(publish_page_physicalpath);
        cmsPage.setPageWebPath(publish_page_webpath);
        cmsPage.setTemplateId(publish_templateId);
        //2.远程调用cms服务的一键发布接口,将课程详情页面发布到服务器
        CmsPostPageResult cmsPostPageResult = cmsPageClient.postPageQuick(cmsPage);
        if (!cmsPostPageResult.isSuccess()) {
            return new CoursePublishResult(CommonCode.FAIL, null);
        }
        //发布成功后,将保存的课程状态改为已发布,202002
        CourseBase courseBase1 = this.saveCourseStatus(courseId, "202002");
        if (courseBase1 == null) {
            return new CoursePublishResult(CommonCode.FAIL, null);
        }
        //保存课程索引信息
        //缓存课程信息
        String pageUrl = cmsPostPageResult.getPageUrl();
        return new CoursePublishResult(CommonCode.SUCCESS, pageUrl);
    }

    /**
     * 更改课程状态
     *
     * @param courseId   课程id
     * @param statusCode 课程状态码
     * @return
     */
    public CourseBase saveCourseStatus(String courseId, String statusCode) {
        //先查出课程信息
        CourseBase courseBase = this.getCoursebaseById(courseId);
        courseBase.setStatus(statusCode);
        return courseBase;
    }
}
