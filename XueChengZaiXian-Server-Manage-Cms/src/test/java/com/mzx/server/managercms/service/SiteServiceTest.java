package com.mzx.server.managercms.service;

import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.server.managecms.ServerManageCmsApp;
import com.mzx.server.managecms.service.ISiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ZhenXinMa
 * @date 2020/2/8 16:12
 */
@SpringBootTest(classes = {ServerManageCmsApp.class})
@RunWith(SpringRunner.class)
public class SiteServiceTest {

    @Autowired
    private ISiteService service;

    @Test
    public void testGet(){
        QueryResponseResult queryResponseResult = service.get();
        System.out.println("0");
    }

}
