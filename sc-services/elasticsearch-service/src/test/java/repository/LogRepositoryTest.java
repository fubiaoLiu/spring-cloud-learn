package repository;

import com.xiaoliu.learn.ElasticsearchServiceApplication;
import com.xiaoliu.learn.model.Log;
import com.xiaoliu.learn.repository.LogRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchServiceApplication.class)
public class LogRepositoryTest {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${server.port}")
    private Integer serverPort;
    @Autowired
    private LogRepository logRepository;

    @Test
    public void shouldSuccessWhenInsertLog() {
        Log log = new Log(1L, new Date(), "BUSINESS", "INFO", applicationName, serverPort, "insert success");
        logRepository.save(log);
    }

    @Test
    public void shouldSuccessWhenInsertLogList() {
        List<Log> list = new ArrayList<>();
        Log log1 = new Log(2L, new Date(), "BUSINESS", "INFO", applicationName, serverPort, "insert success");
        Log log2 = new Log(3L, new Date(), "BUSINESS", "INFO", applicationName, serverPort, "insert success");
        list.add(log1);
        list.add(log2);
        logRepository.saveAll(list);
    }

    /**
     * elasticsearch中没有修改，它的修改原理是该是先删除在新增
     * <p>
     * 修改和新增是同一个接口，区分的依据就是id。
     */
    @Test
    public void shouldSuccessWhenUpdateLog() {
        Log log = new Log(1L, new Date(), "BUSINESS", "WARN", applicationName, serverPort, "update success");
        logRepository.save(log);
    }

    @Test
    public void shouldSuccessWhenFindAllLogs() {
        Iterable<Log> list = logRepository.findAll();
        for (Log log : list) {
            System.out.println(log);
        }
    }

    @Test
    public void shouldGet1WhenFindByLevel_WARN() {
        List<Log> list = logRepository.findByLevel("WARN");
        Assert.assertEquals("WARN Log size not equals 1...", 1, list.size());
        //elasticsearchService.deleteIndex(Log.class);
    }

    @Test
    public void shouldGet0WhenFindByLevel_ERROR() {
        List<Log> list = logRepository.findByLevel("ERROR");
        Assert.assertTrue("ERROR Log list not empty...", list.isEmpty());
    }

    @Test
    public void shouldGet3WhenMatchContext_success() {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("context", "success"));
        // 搜索，获取结果
        Page<Log> logs = this.logRepository.search(queryBuilder.build());
        Assert.assertEquals("success Log list not equals 3...", 3, logs.getTotalElements());
    }

    /**
     * 聚合(分组)统计不同日志等级的文档数、排名
     */
    @Test
    public void shouldSuccessWhenGetStatLevelRank() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为levels，聚合字段为level
        queryBuilder.addAggregation(
                AggregationBuilders.terms("levels").field("level"));
        // 2、查询，需要把结果强转为AggregatedPage类型
        AggregatedPage<Log> aggPage = (AggregatedPage<Log>) this.logRepository.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为levels的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("levels");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即日志等级名称
            System.out.println(bucket.getKeyAsString());
            // 3.5、获取桶中的文档数量
            System.out.println(bucket.getDocCount());
        }
    }
}
