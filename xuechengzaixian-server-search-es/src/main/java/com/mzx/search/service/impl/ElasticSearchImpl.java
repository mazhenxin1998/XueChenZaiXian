package com.mzx.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.mzx.common.model.response.CommonCode;
import com.mzx.common.model.response.QueryResponseResult;
import com.mzx.common.model.response.QueryResult;
import com.mzx.common.model.response.ResponseResult;
import com.mzx.framework.model.course.CoursePub;
import com.mzx.framework.model.search.CourseSearchParam;
import com.mzx.search.service.IElasticService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ZhenXinMa
 * @date 2020/4/29 16:43
 */
@Slf4j
@Service
public class ElasticSearchImpl implements IElasticService {

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

        ResponseResult responseResult = null;
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.timeout("10s");
        // 将我们的数据以JSON的形式放入请求中.
        indexRequest.source(JSON.toJSONString(coursePub), XContentType.JSON);
        try {

            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            responseResult = new ResponseResult(CommonCode.SUCCESS);
        } catch (IOException e) {

            e.printStackTrace();
            responseResult = new ResponseResult(CommonCode.FAIL);
        }

        return responseResult;
    }

    @Override
    public QueryResponseResult searchKeyWord(CourseSearchParam param) {

        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        /*根据关键字进行匹配(关键字也会分词)*/
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(param.getKeyword(),
                "name", "users");
        // 设置匹配占比.
        multiMatchQueryBuilder.minimumShouldMatch("70%");
        // 提升另一个字段的权值.
        multiMatchQueryBuilder.field("name", 10F);
        builder.query(multiMatchQueryBuilder);

        // 高亮设置
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        // 只显示一个高亮
        highlightBuilder.requireFieldMatch(false);
        builder.highlighter(highlightBuilder);
        searchRequest.source(builder);
        // ...
        SearchResponse searchResponse = null;
        try {

            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {

            e.printStackTrace();
        }

        List<CoursePub> coursePub = this.getResponseCoursePub(searchResponse);
        QueryResult result = new QueryResult();
        result.setList(coursePub);
        result.setTotal((long) coursePub.size());

        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @Override
    public ResponseResult delete(@NotNull String id) {

        return null;
    }

    @Override
    public SearchHit[] test(int page, int size, String keyword) {


        if (page < 0) {

            page = 0;
        }
        if (size <= 0) {

            size = 5;
        }

        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // 根据关键字进行匹配
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword,
                "name", "users");
        // 设置匹配占比.
        multiMatchQueryBuilder.minimumShouldMatch("70%");
        // 提升另一个字段的权值.
        multiMatchQueryBuilder.field("name", 10F);
        builder.query(multiMatchQueryBuilder);
        // 分页
        builder.from(page);
        builder.size(size);
        searchRequest.source(builder);
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SearchHits hits = searchResponse.getHits();
        SearchHit[] hits1 = hits.getHits();

        return hits1;
    }

    @Override
    public QueryResponseResult list(int page, int size, CourseSearchParam param) {

        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 先暂时不使用源字段过滤  既将查询的所有字段映射到coursePub类中
        if (!StringUtils.isEmpty(param.getKeyword())) {

            CoursePub pub;
            // 多个字段匹配关键字.
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(
                    param.getKeyword(), "name", "teachplan", "description");
            // 设置匹配占比
            multiMatchQueryBuilder.minimumShouldMatch("70%");
            // 多个字段的话可以提提升某个字段的bosst值
            multiMatchQueryBuilder.field("name", 10);
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }

        // bool查询
        builder.query(boolQueryBuilder);
        searchRequest.source(builder);
        SearchResponse searchResponse = null;
        try {

            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {

            log.info("ElasticSearchImpl中分页查询出现异常...");
            e.printStackTrace();
        }

        // 结果集处理
        List<CoursePub> list = null;
        if (searchResponse != null) {

            list = this.getResponseCoursePub(searchResponse);
        }

        //返回结果.
        QueryResult result = new QueryResult();
        result.setList(list);
        result.setTotal((long) list.size());

        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @Override
    public QueryResponseResult listByCategoryAndGrade(int page, int size, CourseSearchParam param) {

        // 设置索引
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(param.getKeyword())) {

            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(
                    param.getKeyword(), "name", "teachplan", "description");
            // 设置匹配占比
            multiMatchQueryBuilder.minimumShouldMatch("70%");
            // 提升另一个字段的bosst值
            multiMatchQueryBuilder.field("name", 10);
            boolQueryBuilder.must(multiMatchQueryBuilder);
        }

        // 过滤.
        if (!StringUtils.isEmpty(param.getMt())) {

            boolQueryBuilder.filter(QueryBuilders.termQuery("mt", param.getMt()));
        }

        if (!StringUtils.isEmpty(param.getSt())) {

            boolQueryBuilder.filter(QueryBuilders.termQuery("st", param.getSt()));
        }

        if (!StringUtils.isEmpty(param.getGrade())) {

            boolQueryBuilder.filter(QueryBuilders.termQuery("grade", param.getGrade()));
        }

        //bool查询
        builder.query(boolQueryBuilder);
        searchRequest.source(builder);
        SearchResponse searchResponse = null;
        try {

            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.info("按分类和难度等级进行查询的时候出错.");
            e.printStackTrace();
        }

        List<CoursePub> list = null;
        if (searchResponse != null) {

            list = this.getResponseCoursePub(searchResponse);
        }
        QueryResult result = new QueryResult();
        result.setList(list);
        result.setTotal((long) list.size());

        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    @Override
    public QueryResponseResult listByPageSizeAndHighLight(int page, int size, CourseSearchParam param) {

        if (page <= 0) {

            page = 1;
        }

        if (size <= 0) {

            size = 20;
        }

        int start = (page - 1) * size;
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 匹配关键处理.
        if (!StringUtils.isEmpty(param.getKeyword())) {

//            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(
//                    param.getKeyword(), "name");
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", param.getKeyword());
            // 设置匹配占比.
//            multiMatchQueryBuilder.minimumShouldMatch("70%");
            // 提升name字段的boost值
//            multiMatchQueryBuilder.field("name", 10);
            // bool查询
            boolQueryBuilder.must(matchQuery);
        }

//        // 过滤.
//        if (!StringUtils.isEmpty(param.getMt())) {
//
//            boolQueryBuilder.filter(QueryBuilders.termQuery("mt", param.getMt()));
//        }
//
//        if (!StringUtils.isEmpty(param.getSt())) {
//
//            boolQueryBuilder.filter(QueryBuilders.termQuery("st", param.getSt()));
//        }
//
//        if (!StringUtils.isEmpty(param.getGrade())) {
//
//            boolQueryBuilder.filter(QueryBuilders.termQuery("grade", param.getGrade()));
//        }

        // 高亮设置.
        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        highlightBuilder.preTags("<span style='color:#f8f078'>");
        highlightBuilder.postTags("</span>");
        // 只显示一个高亮
        highlightBuilder.requireFieldMatch(false);
        builder.highlighter(highlightBuilder);
//         进行分页.
        builder.from(start);
        builder.size(size);
        // bool查询
        builder.query(boolQueryBuilder);
        // 请求搜索.
        searchRequest.source(builder);
        SearchResponse searchResponse = null;
        try {

            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {

            e.printStackTrace();
        }

        List<CoursePub> list = null;
        if (searchRequest != null) {

            list = this.getResponseCoursePub(searchResponse);
        }

        QueryResult result = new QueryResult();
        result.setList(list);
        result.setTotal((long) list.size());

        return new QueryResponseResult(CommonCode.SUCCESS, result);
    }

    List<CoursePub> getResponseCoursePub(@NotNull SearchResponse searchResponse) {

        /*高亮搜索 返回的结果就是带有特殊标签的结果.*/
        List<CoursePub> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits()) {

            // 解析
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            // name  null
            HighlightField name = highlightFields.get("name");
            // 拿到原来的结果.
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            if (name != null) {

                Text[] texts = name.fragments();
                String n_name = "";
                for (Text text : texts) {

                    n_name += text;
                }

                // 将原先的字段替换为高亮字段
                sourceAsMap.put("name", n_name);
            }

            // 将sourceAsMap中的信息 一个一个的转换为CoursePub类型.
            CoursePub coursePub = this.transform(sourceAsMap);
            list.add(coursePub);
        }

        return list;
    }


    public CoursePub transform(@NotNull Map<String, Object> map) {

        CoursePub coursePub = new CoursePub();
        if (map.get("name") != null) {

            String name = (String) map.get("name");
            coursePub.setName(name);
            /*在这里进行高亮设置.*/

        }

        if (map.get("id") != null) {

            String id = (String) map.get("id");
            coursePub.setId(id);
        }

        if (map.get("charge") != null) {

            String charge = (String) map.get("charge");
            coursePub.setCharge(charge);
        }

        if (map.get("description") != null) {

            String description = (String) map.get("description");
            coursePub.setDescription(description);
        }

        if (map.get("expires") != null) {

            String expires = (String) map.get("expires");
            coursePub.setExpires(expires);
        }

        if (map.get("grade") != null) {

            String grade = (String) map.get("grade");
            coursePub.setGrade(grade);
        }

        if (map.get("pic") != null) {

            String pic = (String) map.get("pic");
            coursePub.setPic(pic);
        }

        if (map.get("price") != null) {

            Double price = (Double) map.get("price");
            coursePub.setPrice(price);
        }

        if (map.get("price_old") != null) {

            Double price_old = (Double) map.get("price_old");
            coursePub.setPrice_old(price_old);
        }

        if (map.get("pubTime") != null) {

            String pubTime = (String) map.get("pubTime");
            coursePub.setPubTime(pubTime);
        }

        if (map.get("qq") != null) {

            String qq = (String) map.get("qq");
            coursePub.setQq(qq);
        }

        if (map.get("studymodel") != null) {

            String studymodel = (String) map.get("studymodel");
            coursePub.setStudymodel(studymodel);
        }

        if (map.get("timestamp") != null) {

            Long timestamp = (Long) map.get("timestamp");
            coursePub.setTimestamp(new Date(timestamp));
        }

        if (map.get("users") != null) {

            String users = (String) map.get("users");
            coursePub.setUsers(users);
        }

        if (map.get("valid") != null) {

            String valid = (String) map.get("valid");
            coursePub.setValid(valid);
        }

        return coursePub;
    }


}
