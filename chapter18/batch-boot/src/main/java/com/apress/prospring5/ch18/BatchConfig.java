package com.apress.prospring5.ch18;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 7/30/17.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobs;
	@Autowired
	private StepBuilderFactory steps;

	@Autowired DataSource dataSource;

	@Autowired SingerItemProcessor itemProcessor;

	@Bean
	public Job job(JobExecutionStatsListener listener) {
		return jobs.get("singerJob").listener(listener) .flow(step1())
				.end()
				.build();
	}

	@Bean
	protected Step step1() {
		return steps.get("step1")
				.<Singer, Singer>chunk(10)
				.reader(itemReader())
				.processor(itemProcessor)
				.writer(itemWriter())
				.build();
	}

	@Bean
	public ItemReader itemReader() {
		FlatFileItemReader<Singer> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new ClassPathResource("support/test-data.csv"));
		itemReader.setLineMapper(new DefaultLineMapper<Singer>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[] { "firstName", "lastName", "song" });
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<Singer>() {{
				setTargetType(Singer.class);
			}});
		}});
		return itemReader;
	}

	@Bean
	public ItemWriter itemWriter() {
		JdbcBatchItemWriter<Singer> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		itemWriter.setSql("INSERT INTO SINGER (first_name, last_name, song) VALUES (:firstName, :lastName, :song)");
		itemWriter.setDataSource(dataSource);
		return itemWriter;
	}
}
