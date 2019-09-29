package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.utils.StringUtil;
import com.xuecheng.manage_course.dao.CourseBaseRepository;
import com.xuecheng.manage_course.dao.TeachplanMapper;
import com.xuecheng.manage_course.dao.TeachplanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * 查询课程计划
     *
     * @param courseId
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
}
