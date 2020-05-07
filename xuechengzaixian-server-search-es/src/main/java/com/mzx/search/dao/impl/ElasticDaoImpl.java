package com.mzx.search.dao.impl;

import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.search.CourseSearchParam;
import com.mzx.search.dao.IElasticDao;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author ZhenXinMa
 * @date 2020/4/29 16:45
 */
@Component
public class ElasticDaoImpl implements IElasticDao {

    @Resource
    @Qualifier(value = "restHighLevelClient")
    private RestHighLevelClient client;

    /**
     * 固定的值可以放在外面的.
     */
    @Value(value = "${xuechengzaixian.elasticsearch.index}")
    private String index;

    @Override
    public ResponseResult addIndex(CoursePub coursePub) {


        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.timeout("3s");
        // 将我们的数据以JSON的形式放入请求中.
        indexRequest.source(coursePub, XContentType.JSON);
        try {

            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public QueryResponseResult search(CourseSearchParam param) {

        /*高亮查询*/
        return null;
    }
}
