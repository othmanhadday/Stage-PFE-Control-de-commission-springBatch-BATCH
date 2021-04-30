package com.hadday.commissionbatch.batch;

import com.hadday.commissionbatch.entities.AllFeesGenerated;
import com.hadday.commissionbatch.entities.ReleveSolde;
import com.hadday.commissionbatch.entities.Ssatf;
import com.hadday.commissionbatch.mapper.ReleveSoldeDbRowMapper;
import com.hadday.commissionbatch.mapper.SsatfDbRowMapper;
import com.hadday.commissionbatch.processor.AvoirItemProcessor;
import com.hadday.commissionbatch.processor.SsatfItemProcessor;
import com.hadday.commissionbatch.writer.AvoirItemWriter;
import com.hadday.commissionbatch.writer.SsatfItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private SsatfItemWriter ssatfItemWriter;
    @Autowired
    private SsatfItemProcessor ssatfItemProcessor;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AvoirItemProcessor avoirItemProcessor;
    @Autowired
    private AvoirItemWriter avoirItemWriter;


    @Bean
    public Job jobDemo() throws Exception {
        return jobBuilderFactory.get("job")
                .start(stepSsatf())
                .next(stepReleveSolde())
                .build();
    }

    @Bean
    public Step stepSsatf() throws Exception {
        return stepBuilderFactory.get("step_ssatf")
                .<Ssatf, AllFeesGenerated>chunk(100)
                .reader(ssatfDbReader())
                .processor(ssatfItemProcessor)
                .writer(ssatfItemWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Step stepReleveSolde() throws Exception {
        return stepBuilderFactory.get("step_releveSolde")
                .<ReleveSolde, AllFeesGenerated>chunk(100)
                .reader(avoirsDbReader())
                .processor(avoirItemProcessor)
                .writer(avoirItemWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(4);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }

    @Bean
    @StepScope
    public ItemReader<Ssatf> ssatfDbReader() throws Exception {
        return (ItemStreamReader<Ssatf>) itemStreamReader(new SsatfDbRowMapper(),
                "select * ",
                "from ssatf",
                "where DATE(date_alimentation) = CURRENT_DATE()");
    }

    @Bean
    @StepScope
    public ItemStreamReader<ReleveSolde> avoirsDbReader() throws Exception {
        return (ItemStreamReader<ReleveSolde>) itemStreamReader(new ReleveSoldeDbRowMapper(),
                "select * ",
                "from releve_solde ",
                "where DATE(date_alimentation) = CURRENT_DATE()");
    }

    public ItemStreamReader<? extends Object> itemStreamReader(RowMapper rowMapper, String select, String from, String where) throws Exception {
        JdbcPagingItemReader<Object> reader = new JdbcPagingItemReader<Object>();
        reader.setDataSource(dataSource);
        final SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean =
                new SqlPagingQueryProviderFactoryBean();
        sqlPagingQueryProviderFactoryBean.setDataSource(dataSource);
        sqlPagingQueryProviderFactoryBean.setDataSource(dataSource);
        sqlPagingQueryProviderFactoryBean.setSelectClause(select);
        sqlPagingQueryProviderFactoryBean.setFromClause(from);
//        sqlPagingQueryProviderFactoryBean.setWhereClause(where);
        sqlPagingQueryProviderFactoryBean.setSortKey("id");
        reader.setQueryProvider(sqlPagingQueryProviderFactoryBean.getObject());
        reader.setPageSize(500);
        reader.setRowMapper(rowMapper);
        reader.afterPropertiesSet();
        reader.setSaveState(false);
        return reader;
    }

}
