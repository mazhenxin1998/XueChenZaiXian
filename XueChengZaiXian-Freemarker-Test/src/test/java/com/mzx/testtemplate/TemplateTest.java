package com.mzx.testtemplate;

import com.mzx.freemaker.FreemakerApp;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/2/10 23:13
 */
@SpringBootTest(classes = {FreemakerApp.class})
@RunWith(SpringRunner.class)
public class TemplateTest {

    @Resource
    private RestTemplate restTemplate;

    @Test
    public void testTemplate() throws IOException, TemplateException {

        // 创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());

        // 得到当前类的路径
        String classpath = this.getClass().getResource("/").getPath();
        System.out.println(classpath);

        //定义模板路径
        configuration.setDirectoryForTemplateLoading(new File(classpath+"/templates/"));

        Template template = configuration.getTemplate("test1.ftl");

        // 数据模型
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");

        // 静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template,map);
        System.out.println(content);

        // 将字符串转换为流
        InputStream inputStream = IOUtils.toInputStream(content);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/test1.html"));
        int copy = IOUtils.copy(inputStream, fileOutputStream);
    }

    /**
     * 使用基于字符串生成静态文件
     */
    @Test
    public void t2() throws IOException, TemplateException {

        // 创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());

        // 使用简单的字符串进行模板测试
        String templateString = "" +
                "<html>\n" +
                " <head></head>\n" +
                " <body>\n" +
                " 名称：${name}\n" +
                " </body>\n" +
                "</html>";

        //模板加载器
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template", templateString);
        configuration.setTemplateLoader(stringTemplateLoader);
        //得到模板
        Template template = configuration.getTemplate("template", "utf‐8");
        //数据模型
        Map<String, Object> map = new HashMap<>();
        map.put("name", "黑马程序员");
        //静态化
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        //静态化内容
        System.out.println(content);

        InputStream inputStream = IOUtils.toInputStream(content);
        //输出文件
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/test1.html"));
        IOUtils.copy(inputStream, fileOutputStream);


    }

    @Test
    public String t3(){

        ResponseEntity<Map> entity = restTemplate.getForEntity("http://localhost:31200/course/" +
                "courseView/4028e581617f945f01617f9dabc40000", Map.class);
        Map map = entity.getBody();

        return "course";

    }

}
