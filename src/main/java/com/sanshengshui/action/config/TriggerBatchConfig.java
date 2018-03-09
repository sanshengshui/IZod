package com.sanshengshui.action.config;

import com.sanshengshui.action.batch.CsvBeanValidator;
import com.sanshengshui.action.batch.CsvItemProcessor;
import com.sanshengshui.action.batch.CsvJobListener;
import com.sanshengshui.action.entity.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class TriggerBatchConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<Person>
    reader(@Value("#{jobParameters['input.file.name']}") String pathToFile){
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
        reader.setResource(new ClassPathResource(pathToFile));
        reader.setEncoding("GBK");
        reader.setLineMapper(new DefaultLineMapper<Person>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(new String[] {"name","age","nation","address"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){
                    {
                        setTargetType(Person.class);
                    }
                });
            }
        });
        return reader;
    }
    @Bean
    public ItemProcessor<Person,Person> processor(){
        /**
         * 使用我们自己定义的ItemProcessor的实现CsvItemProcessor。
         */
        CsvItemProcessor processor = new CsvItemProcessor();
        /**
         * 为processor指定效验器为CsvBeanValidator
         */
        processor.setValidator(csvBeanValidator());
        return processor;
    }


    @Bean
    /**
     * Spring指让容器中已有的Bean以参数的形式注入，Spring Boot已为我们定义了dataSource
     */
    public ItemWriter<Person> writer(DataSource dataSource){
        /**
         * 我们使用JDBC批处理的JdbcBatchItemWriter来写数据到数据库
         */
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
        String sql = "insert into person" + "(name,age,nation,address) " +
                " values( :name, :age, :nation, :address)";
        /**
         * 在此设置要执行批处理的SQL语句
         */
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }


    @Bean
    /**
     * jobRepository的定义需要dataSource和transationManager,Spring Boot已为我们自动配置了这2个类，
     * Spring可通过方法注入已有的Bean。
     */
    public JobRepository jobRepository(DataSource dataSource,
                                       PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean =
                new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        return jobRepositoryFactoryBean.getObject();
    }


    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource,
                                         PlatformTransactionManager transactionManager) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource,transactionManager));
        return jobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1){
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .listener(csvJobListener())
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer,
                      ItemProcessor<Person,Person> processor){
        return stepBuilderFactory
                .get("step1")
                .<Person,Person>chunk(65000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public CsvJobListener csvJobListener(){
        return new CsvJobListener();
    }

    @Bean
    public Validator<Person> csvBeanValidator(){
        return new CsvBeanValidator<Person>();
    }
}
