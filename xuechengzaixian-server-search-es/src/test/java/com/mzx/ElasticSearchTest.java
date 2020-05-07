package com.mzx;

import com.alibaba.fastjson.JSON;
import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.search.CourseSearchParam;
import com.mzx.framework.model.search.es.ElasticSearchMappingBean;
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
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.context.HierarchicalThemeSource;

import javax.xml.transform.Source;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
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

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("xc_course");
        try {
            /*设置索引参数*/
            createIndexRequest.settings(Settings.builder().put("number_of_shards", 1)
                    .put("number_of_replicas", 0));
            /*设置映射*/


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

        ElasticSearchMappingBean bean = new ElasticSearchMappingBean();
        bean.setDescription("SpringBoot 在java领域非常流行，java程序员都在用")
                .setName("Tomcat框架")
                .setPic("222")
                .setPrice(222)
                .setStudymodel("201002")
                .setTimestamp(new Date());
        IndexRequest indexRequest = new IndexRequest("xc_course");
        /*为indexRequest设置一些属性*/
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        /*将我们数据让如请求中.*/
        indexRequest.source(JSON.toJSONString(bean), XContentType.JSON);
        /*客户端发送请求*/
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, DEFAULT);
        /*查看响应结果
         * indexResponse里面封装了相应的具体结果.*/
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status().toString());

    }

    @Test
    public void addDocumentPub() {

        /**
         *  明天在测试.
         *  ...
         */
        CoursePub coursePub = new CoursePub();
        coursePub.setCharge("XXX")
                .setDescription("Bos物流项目2.0是传智播客推出的高级项目课程，课程包括了项目概述、系统管理、用户管理、运单管理、统计分析等模块，深入浅出的讲解了当前的流行技术。")
                .setExpires("XXX")
                .setGrade("200001")
                .setId("402885816243d2dd016243f24c030002")
                .setName("Bos物流项目2.0")
                .setPic("http://img13.360buyimg.com/n7/jfs/t1/102900/26/2632/158701/5dd601a5E9ed34588/596e136d4a144cae.jpg")
                .setPrice(99.0)
                .setPrice_old(199.0)
                .setPubTime("xxX")
                .setQq("2280480546")
                .setSt("1-3-2")
                .setStudymodel("201001")
                .setTeachplan("XX")
                .setTeachmode("Template")
                .setTimestamp(new Date())
                .setUsers("本项目适应于具有一定java基础的开发人员。.")
                .setValid("204002")
                .setMt("1-3");
        IndexRequest indexRequest = new IndexRequest("course");
        indexRequest.source(JSON.toJSONString(coursePub), XContentType.JSON);
        try {
            restHighLevelClient.index(indexRequest, DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }


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

        DeleteRequest deleteRequest = new DeleteRequest("course", "-ED9yXEBZq6x8UvjIVi2");
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

        SearchRequest searchRequest = new SearchRequest("xc_course");

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

    @Test
    public void searchMatchAll() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xc_course");

        /*构建搜索条件*/
        SearchSourceBuilder builder = new SearchSourceBuilder();
        /*设置查询条件*/
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        builder.query(matchAllQueryBuilder);
        /*source源字段过滤*/
        builder.fetchSource(new String[]{"name", "studymodel"}, new String[]{});
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

    @Test
    public void searchPage() throws IOException {

        SearchRequest searchRequest = new SearchRequest("xc_course");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        builder.query(matchAllQueryBuilder);
        /* 分页设置起始下标*/
        builder.from(0);
        // 设置每页大小
        builder.size(1);
        // source原字段过滤
        builder.fetchSource(new String[]{"name", "studymodel"}, new String[]{});
        searchRequest.source(builder);
        SearchResponse search = restHighLevelClient.search(searchRequest, DEFAULT);
        for (SearchHit hit : search.getHits()) {

            System.out.println(hit.getSourceAsMap());
        }

    }

    @Test
    public void searchMatch() throws IOException {

        /*根据关键字搜索*/
        SearchRequest searchRequest = new SearchRequest("xc_course");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("description", "用").operator(Operator.OR);
        builder.query(matchQueryBuilder);
        /*源字段过滤*/
        builder.fetchSource(new String[]{"name", "studymodel"}, new String[]{});
        searchRequest.source(builder);
        SearchResponse search = restHighLevelClient.search(searchRequest, DEFAULT);
        for (SearchHit hit : search.getHits()) {

            System.out.println(hit.getSourceAsMap());
        }
        System.out.println(1);

    }

    @Test
    public void searchMultiQuery() throws IOException {

        SearchRequest searchRequest = new SearchRequest("xc_course");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.fetchSource(new String[]{"name", "pic", "description", "studymodel"}, new String[]{});
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders
                .multiMatchQuery("spring开发框架", "name", "description")
                .minimumShouldMatch("50%");
        multiMatchQueryBuilder.field("name", 10);
        /*多多个字段进行查询*/
        SearchResponse search = restHighLevelClient.search(searchRequest, DEFAULT);
        for (SearchHit hit : search.getHits()) {

            System.out.println(hit.getSourceAsMap());
        }

        System.out.println(1);
    }

    @Test
    public void searchHighLight() throws IOException {

        /*
         *   报错.
         * */
        SearchRequest searchRequest = new SearchRequest("xc_course");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        /*源字段过滤*/
        builder.fetchSource(new String[]{"name", "studymodel", "price", "description"}, new String[]{});

        /*多个字段进行匹配*/
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("开发", "name", "description");
        builder.query(multiMatchQueryBuilder);
        /*布尔查询实现将多个查询结果组合起来*/
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(builder.query());
        /*过滤*/
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(100).lte(200));
        /*排序*/
        builder.sort(new FieldSortBuilder("studymodel").order(SortOrder.DESC));
        builder.sort(new FieldSortBuilder("price").order(SortOrder.ASC));
        /*高亮设置*/
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<tag>");
        highlightBuilder.postTags("</tag>");
        // 设置高亮字段
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        builder.highlighter(highlightBuilder);
        searchRequest.source(builder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, DEFAULT);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hitsHits = hits.getHits();
        for (SearchHit hitsHit : hitsHits) {

            Map<String, Object> map = hitsHit.getSourceAsMap();
            String name = (String) map.get("name");
            Map<String, HighlightField> highlightFields = hitsHit.getHighlightFields();
            if (highlightFields != null) {

                HighlightField field = highlightFields.get("name");
                if (field != null) {

                    Text[] texts = field.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text text : texts) {

                        stringBuffer.append(text);
                    }

                    name = stringBuffer.toString();
                }

            }

            String index = hitsHit.getIndex();
            String type = hitsHit.getType();
            String id = hitsHit.getId();
            float score = hitsHit.getScore();
            String sourceAsString = hitsHit.getSourceAsString();
            String studymodel = (String) map.get("studymodel");
            String description = (String) map.get("description");
            System.out.println(name);
            System.out.println(studymodel);
            System.out.println(description);
        }

        System.out.println(1);
    }

    @Test
    public void searchHighLi() throws IOException {

        SearchRequest searchRequest = new SearchRequest("xc_course");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 分页
        builder.from(0);
        builder.size(5);
        // 精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "spring开发框架");
        builder.query(termQueryBuilder);
        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("<span style='color:red'>");
        // 只显示一个高亮
        highlightBuilder.requireFieldMatch(false);
        builder.highlighter(highlightBuilder);
        searchRequest.source(builder);

        SearchResponse search = restHighLevelClient.search(searchRequest, DEFAULT);
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : search.getHits()) {
            // 解析高亮
            Map<String, HighlightField> fields = hit.getHighlightFields();
            HighlightField name = fields.get("name");
            // 拿到原来的结果
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            // 解析高亮字段
            if (name != null) {

                Text[] texts = name.fragments();
                String n_name = "";
                for (Text text : texts) {

                    n_name += text;
                }

                // 将原先的字段换为查询的高亮字段
                sourceAsMap.put("name", n_name);

            }
            list.add(sourceAsMap);
        }

    }

    @Test
    public void searchHighOne() {

        CourseSearchParam param = new CourseSearchParam();
        if (param.getPage() <= 0) {
            System.out.println(1);
        }
       
    }


}
