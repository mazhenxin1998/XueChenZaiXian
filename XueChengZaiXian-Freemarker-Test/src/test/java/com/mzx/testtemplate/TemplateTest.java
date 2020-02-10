package com.mzx.testtemplate;

import com.mzx.freemaker.FreeMakerApp;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

/**
 * @author ZhenXinMa
 * @date 2020/2/10 23:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TemplateTest {

    @Test
    public void testTemplate() throws IOException , TemplateException {

        // 创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());

        // 设置模板路径
        String path = this.getClass().getResource("/").getPath();
        configuration.setDirectoryForTemplateLoading(new File(path+"/templates"));

        // 谁知字符集
        configuration.setDefaultEncoding("utf-8");

        // 加载模板
        Template template = configuration.getTemplate("test1.ftl1");
        

    }

}
