package com.mzx.search.controller;

import com.mzx.search.service.IElasticService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.client.RequestOptions.DEFAULT;

/**
 * @author ZhenXinMa
 * @date 2020/4/27 17:39
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Resource
    private IElasticService service;

    @GetMapping(value = "/t")
    public String t1() {

        CreateIndexRequest createIndexRequest = new CreateIndexRequest("mzx");
        try {
            restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            return "SUCCESS 增加 index";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "FALL  add  index";
    }

    @GetMapping(value = "/get/{name}/{text}")
    public Object t2(@PathVariable(value = "name", required = true) String name,
                     @PathVariable(value = "text") String text) {

        SearchRequest searchRequest = new SearchRequest("xc_course");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(name, text);
        builder.query(matchQueryBuilder);
        /*从源字段中过滤*/
        builder.fetchSource(new String[]{"name", "timestamp"}, new String[]{});
        searchRequest.source(builder);
        List<Map> list = new ArrayList<>();
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = search.getHits();
            for (SearchHit hit : hits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                list.add(sourceAsMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }

    @GetMapping(value = "/search/high")
    public Object t3() {

        SearchRequest searchRequest = new SearchRequest("xc_course");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        // 精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "spring");
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        builder.query(termQueryBuilder);
        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        // 只显示一个高亮
        highlightBuilder.requireFieldMatch(false);
        builder.highlighter(highlightBuilder);
        searchRequest.source(builder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        System.out.println(1);
        return list;
    }

    @GetMapping(value = "/high")
    public String t4(String value) {

        int page = 1;
        int size = 20;

        StringBuilder stringBuilder = new StringBuilder();

        SearchRequest searchRequest = new SearchRequest("course");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        // 精准匹配
//        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", value);
//        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", value);
        builder.query(matchQuery);
        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<span style='color:#f8f078'>");
        highlightBuilder.postTags("</span>");
        // 只显示一个高亮
        highlightBuilder.requireFieldMatch(false);
        builder.highlighter(highlightBuilder);
        // 设置分页
        builder.from(page);
        builder.size(size);
        searchRequest.source(builder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest, DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map<String, Object>> list = new ArrayList<>();
        String result = "";
        for (SearchHit hit : search.getHits())
        {
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
                result = this.appendString(stringBuilder, n_name, "<br>");
            }
            list.add(sourceAsMap);
        }

        System.out.println(1);
        return result;
    }

    @GetMapping(value = "/t5")
    public SearchHit[] t5(int page,int size,String key){

        SearchHit[] test = service.test(page, size, key);
        return test;
    }

    @GetMapping(value = "/t6")
    public Object t6(){
        Map<Integer, Object> map = new HashMap();
        map.put(1,"张三");
        map.put(2,"张三");
        map.put(3,"张三");

        return map;

    }


    public String appendString(StringBuilder builder, String... args) {

        for (String arg : args) {

            builder.append(arg);
        }

        return builder.toString();
    }

}
