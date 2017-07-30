package com.apress.prospring5.ch18.config;

import com.apress.prospring5.ch18.Singer;
import com.apress.prospring5.ch18.StepExecutionStatsListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Autowired DataSource dataSource;

	@Autowired ResourceLoader resourceLoader;

	@Autowired StepExecutionStatsListener executionStatsListener;

	@Bean
	public Job job(@Qualifier("step1") Step step1) {
		return jobs.get("singerJob").start(step1).build();
	}

	@Bean
	protected Step step1(ItemReader<Singer> reader, ItemProcessor<Singer,Singer> itemProcessor, ItemWriter<Singer> writer) {
		return steps.get("step1").listener(executionStatsListener)
				.<Singer, Singer>chunk(10)
				.reader(reader)
				.processor(itemProcessor)
				.writer(writer)
				.build();
	}

	@Bean
	public ItemReader itemReader() {
		FlatFileItemReader itemReader = new FlatFileItemReader();
		itemReader.setResource(resourceLoader.getResource("classpath:support/test-data.csv"));
		DefaultLineMapper lineMapper = new DefaultLineMapper();

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setNames("firstName","lastName","song");
		tokenizer.setDelimiter(",");
		lineMapper.setLineTokenizer(tokenizer);

		BeanWrapperFieldSetMapper<Singer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Singer.class);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		itemReader.setLineMapper(lineMapper);
		return itemReader;
	}

	@Bean
	public ItemWriter itemWriter() {
		JdbcBatchItemWriter<Singer> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		itemWriter.setSql("INSERT INTO singer (first_name, last_name, song) VALUES (:firstName, :lastName, :song)");
		itemWriter.setDataSource(dataSource);
		return itemWriter;
	}
}
