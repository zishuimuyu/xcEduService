package com.xuecheng.test.freemarker;

import com.xuecheng.test.freemarker.model.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * TODO
 *
 * @author：GJH
 * @createDate：2019/9/21
 * @company：洪荒宇宙加力蹲大学
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FreemarkerTest {
    //基于ftl 模板文件生成html文件
    @Test
    public void testGenerateHtml() throws IOException, TemplateException {
        //定义配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //定义模板
        //获取资源根路径
      //  String path = this.getClass().getResource("/").getPath();
        String path="D:\\Projects\\Java\\XcEdu\\xcEduService\\test-freemarker\\src\\test\\resources";
        //定义模板路径
        configuration.setDirectoryForTemplateLoading(new File(path + "/templates/"));
        //根据模板名称获取模板
        Template template = configuration.getTemplate("test1.ftl");
        //定义数据模型
        Map map = getMap();
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        InputStream inputStream = IOUtils.toInputStream(content);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/Projects/Java/XcEdu/html", "test1.Html"));
        //输出文件
        IOUtils.copy(inputStream, fileOutputStream);
        inputStream.close();
        fileOutputStream.close();

    }
    @Test
    public void testGenerateHtmlByContent(){
        //定义配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        //jixu
    }

    //数据模型
    private Map getMap() {
        Map<String, Object> map = new HashMap<>();
        //向数据模型放数据
        map.put("name", "黑马程序员");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);
//        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
        //向数据模型放数据
        map.put("stus", stus);
        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        //向数据模型放数据
        map.put("stu1", stu1);
        //向数据模型放数据
        map.put("stuMap", stuMap);
        return map;
    }
}
