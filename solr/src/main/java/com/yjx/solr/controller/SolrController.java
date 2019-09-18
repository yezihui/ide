package com.yjx.solr.controller;

import com.yjx.solr.model.Person;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.FieldAnalysisRequest;
import org.apache.solr.client.solrj.response.AnalysisResponseBase;
import org.apache.solr.client.solrj.response.FieldAnalysisResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** 
 * <p> 
 * solr测试控制类
 * </p> 
 *
 * @author yejx 
 * @date 2019/7/25 15:46
 */ 
@RestController
@RequestMapping("/solr")
public class SolrController {

    @Resource
    private SolrTemplate solrTemplate;

    private static final String COLLECTION = "new_core";

    /**
     * or或者 条件
     * criteria = criteria.or("name").contains("1");
     *
     * boost 计算权重 改变默认排序
     * criteria = criteria.and("name").boost(2.0F).contains("i2d")
     *
     * not 不包含
     * criteria = criteria.or("name").contains("1").not();
     *
     * 过滤 满足条件留下
     * SimpleFilterQuery simpleFilterQuery = new SimpleFilterQuery(criteria.and("id").boost(Float.NaN).contains("2").not());
     */

    @GetMapping
    public List<Person> testSpringQuery() {
        // 查询条件
        Criteria criteria = new Criteria("description").contains("档");

        // 排序规则
        Sort sort = new Sort(Sort.Direction.ASC, "id");

        Query query = new SimpleQuery(criteria);
        query.addSort(sort);

        Page<Person> page = solrTemplate.query(COLLECTION, query, Person.class);
        System.out.println("----" + System.currentTimeMillis());
        return page.getContent();
    }

    @GetMapping("/{id}")
    public String testDeleteSolr(@PathVariable("id") String id) {
        UpdateResponse response = solrTemplate.delete(COLLECTION, new SimpleQuery(new Criteria("id").contains(id)));
        return response.getStatus() + "--" + response.getQTime();
    }


    @GetMapping("/add")
    public String addBean() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("id11", "基于java bean的添加", "通过java bean完成添加 java bean的添加附件"));
        list.add(new Person("id2", "基于java bean的列表数据的添加", "测试如何通过一个对象完成添加"));
        UpdateResponse response = solrTemplate.saveBean(COLLECTION, list.get(0));

        return response.getStatus() + "--" + response.getQTime();
    }

    @GetMapping("/analysis")
    public String analysis() {
        FieldAnalysisRequest request = new FieldAnalysisRequest("/analysis/field");
        request.addFieldName("description");
        request.setFieldValue("text_ik");
        request.setQuery("DevNote与大家分享开发实践经验");
        request.setQuery("基于java bean的列表数据的添加");
        request.setQuery("谢天谢地总算可以了");
        HttpSolrClient server = new HttpSolrClient("http://127.0.0.1:8080/solr/new_core");
        try {
            FieldAnalysisResponse response = request.process(server);
            List<String> results = new ArrayList<>();
            for (AnalysisResponseBase.AnalysisPhase phase : response.getFieldNameAnalysis("description")
                    .getQueryPhases()) {
                List<AnalysisResponseBase.TokenInfo> list = phase.getTokens();
                for (AnalysisResponseBase.TokenInfo info : list) {
                    results.add(info.getText());
                }

            }
            for (String str : results) {
                System.out.println(str);
            }
            return results.toString();
        } catch (SolrServerException | IOException e) {
            System.out.println(e);
        }
        return "fail";
    }
}
