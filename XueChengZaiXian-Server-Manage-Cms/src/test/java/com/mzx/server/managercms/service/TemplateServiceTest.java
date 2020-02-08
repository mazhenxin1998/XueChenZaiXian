package com.mzx.server.managercms.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.server.managecms.ServerManageCmsApp;
import com.mzx.server.managecms.service.ITemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 22:18
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class TemplateServiceTest {

    @Autowired
    private ITemplateService service;

    @Test
    public void get(){
        QueryResponseResult queryResponseResult = service.get();
        System.out.println(1);
    }

}
