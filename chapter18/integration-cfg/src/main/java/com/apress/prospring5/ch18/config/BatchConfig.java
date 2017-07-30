package com.apress.prospring5.ch18.config;

import com.apress.prospring5.ch18.Singer;
import com.apress.prospring5.ch18.SingerItemProcessor;
import com.apress.prospring5.ch18.StepExecutionStatsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 7/30/17.
 */
@Configuration
@EnableBatchProcessing
@Import(DataSourceConfig.class)
@ComponentScan("com.apress.prospring5.ch18")
public class BatchConfig {

	private static Logger logger = LoggerFactory.getLogger(BatchConfig.class);
	@Autowired
	private JobBuilderFactory jobs;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private StepBuilderFactory steps;
	@Autowired DataSource dataSource;
	@Autowired StepExecutionStatsListener executionStatsListener;
	@Autowired SingerItemProcessor itemProcessor;
	@Autowired ResourceLoader resourceLoader;

	@Bean
	public Job singerJob() {
		return jobs.get("singerJob").start(step1()).build();
	}

	@Bean
	protected Step step1() {
		return steps.get("step1").listener(executionStatsListener)
				.<Singer, Singer>chunk(10)
				.reader(itemReader(null))
				.processor(itemProcessor)
				.writer(itemWriter())
				.build();
	}

	@Bean
	@StepScope
	public FlatFileItemReader itemReader(@Value("file://#{jobParameters['file.name']}") String filePath) {
		FlatFileItemReader itemReader = new FlatFileItemReader();
		itemReader.setResource(resourceLoader.getResource(filePath));
		DefaultLineMapper lineMapper = new DefaultLineMapper();
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames("firstName", "lastName", "song");
		tokenizer.setDelimiter(",");
		lineMapper.setLineTokenizer(tokenizer);
		BeanWrapperFieldSetMapper<Singer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Singer.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		itemReader.setLineMapper(lineMapper);
		return itemReader;
	}

	@Bean
	public ItemWriter<Singer> itemWriter() {
		JdbcBatchItemWriter<Singer> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		itemWriter.setSql("INSERT INTO singer (first_name, last_name, song) VALUES (:firstName, :lastName, :song)");
		itemWriter.setDataSource(dataSource);
		return itemWriter;
	}
}
