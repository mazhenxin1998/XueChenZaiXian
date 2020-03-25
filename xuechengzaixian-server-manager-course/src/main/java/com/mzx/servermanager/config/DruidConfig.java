package com.mzx.servermanager.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/3/22 22:23
 */
@Configuration
@Component
public class DruidConfig {

    /**
     *  对Druid的监控界面进行登录设置以及黑白名单.
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){

        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParam = new HashMap<>(10);

        initParam.put("loginUsername","mzx123");
        initParam.put("loginPassword","mzx123");
        //  allow  默认是允许的
        initParam.put("allow","");
        initParam.put("deny","192.168.1.107");
        initParam.put("deny","192.168.1.107");
        bean.setInitParameters(initParam);

        return bean;
    }

    /**
     *  对Druid监控界面进行资源拦截.
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistration(){

        FilterRegistrationBean bean = new FilterRegistrationBean();
        //  设置参数
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParam = new HashMap<>(10);
        //  设置默认 对静态资源不拦截
        initParam.put("exclusions","*.js,*.css,*.js,/druid/*");
        bean.setUrlPatterns(Arrays.asList("/*"));
        bean.setInitParameters(initParam);

        return bean;
    }


}
