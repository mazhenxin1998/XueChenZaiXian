package com.mzx;

import com.alibaba.fastjson.JSON;
import com.mzx.search.SpringApplicationSearchApp;
import com.mzx.search.bean.UserBean;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.transform.Source;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.client.RequestOptions.DEFAULT;

/**
 * @author ZhenXinMa
 * @date 2020/4/27 18:11
 */
@SpringBootTest(classes = {SpringApplicationSearchApp.class})
@RunWith(SpringRunner.class)
public class ElasticSearchTest {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void t1() {

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("user");
        try {
            restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    @Test
    public void getIndex() throws IOException {

        GetIndexRequest getIndexRequest = new GetIndexRequest("mzx");
        boolean exists = restHighLevelClient.indices().exists(getIndexRequest, DEFAULT);
        System.out.println(1);

    }

    @Test
    public void deleteIndex() throws IOException {

        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("mzx");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest,
                DEFAULT);
        System.out.println(delete.isAcknowledged());

    }

    @Test
    public void addDocument() throws IOException {

        UserBean userBean = new UserBean("张三", 28);
        IndexRequest indexRequest = new IndexRequest("user");
        /*为indexRequest设置一些属性*/
        indexRequest.id("2");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        /*将我们数据让如请求中.*/
        indexRequest.source(JSON.toJSONString(userBean), XContentType.JSON);
        /*客户端发送请求*/
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, DEFAULT);
        /*查看响应结果
         * indexResponse里面封装了相应的具体结果.*/
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status().toString());

    }

    @Test
    public void getDocument() throws IOException {

        GetRequest request = new GetRequest("user", "1");
        /* 不获取GetRequest的上下文效率更高.
        * 用于判断索引库中是否存在该值的判断.
        request.fetchSourceContext(new FetchSourceContext(false));*/
        /*判断该文档是否存在.*/
        GetResponse getResponse = restHighLevelClient.get(request, DEFAULT);
        String source = getResponse.getSourceAsString();
        /**/
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        System.out.println(source);
        System.out.println(1);

    }


    @Test
    public void updateDocument() throws IOException {

        UpdateRequest updateRequest = new UpdateRequest("user", "1");
        UserBean userBean = new UserBean("马振鑫聊Java", 100);
        updateRequest.doc(JSON.toJSONString(userBean), XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, DEFAULT);
        System.out.println(updateResponse);
    }

    @Test
    public void deleteDocument() throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest("user", "2");
        // 设置超时时间
        deleteRequest.timeout("1s");
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, DEFAULT);
        System.out.println(deleteResponse);


    }

    @Test
    public void bulkInsertDocument() throws IOException {

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("15s");
        List<UserBean> list = new ArrayList<>();
        list.add(new UserBean("马振鑫", 1));
        list.add(new UserBean("马振鑫", 2));
        list.add(new UserBean("马振鑫", 3));
        list.add(new UserBean("马振鑫", 4));
        for (int i = 0; i < list.size(); i++) {

            bulkRequest.add(new IndexRequest("user")
                    .id("" + (i + 1))
                    .source(JSON.toJSONString(list.get(i)), XContentType.JSON));

        }

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, DEFAULT);
        System.out.println(bulkResponse.hasFailures());

    }

    @Test
    public void search() throws IOException {

        SearchRequest searchRequest = new SearchRequest("user");
        /*构建搜索条件*/
        SearchSourceBuilder builder = new SearchSourceBuilder();
        /*设置查询条件*/
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "马振鑫");
        builder.query(termQueryBuilder);
        /*设置超时时间*/
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        /*查询*/
        searchRequest.source(builder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, DEFAULT);
        System.out.println(searchResponse.getHits().toString());
        /*获取每个值*/
        for (SearchHit hit : searchResponse.getHits()) {

            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println(sourceAsMap.toString());
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);

        }

        System.out.println(1);
    }


}
