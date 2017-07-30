/*
package com.apress.prospring5.ch18.config;

import com.apress.prospring5.ch18.MessageToJobLauncher;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.integration.launch.JobLaunchingGateway;
import org.springframework.batch.integration.launch.JobLaunchingMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.*;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.messaging.MessageChannel;

import java.io.File;

*/
/**
 * Created by iuliana.cosmina on 7/30/17.
 *//*

//@Configuration
//@EnableIntegration
//@IntegrationComponentScan
//@Import(BatchConfig.class)
public class IntegrationConfig {

	@Autowired Job job;

	@Bean
	public MessageChannel inbound() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel outbound() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel loggingChannel() {
		return new DirectChannel();
	}

	@Bean
	@InboundChannelAdapter(channel = "inbound", poller = @Poller(fixedRate = "1000"))
	public MessageSource<File> inboundFileChannelAdapter() {
		FileReadingMessageSource source = new FileReadingMessageSource();
		source.setDirectory(new File("file:/Users/iuliana.grajdeanu/temp/"));
		source.setFilter(new SimplePatternFileListFilter("*.csv"));
		return source;
	}

	@Bean
	@Transformer(inputChannel = "inbound", outputChannel = "outbound")
	public MessageToJobLauncher messageToJobLauncher() {
		return new MessageToJobLauncher(job, "file.name");
	}

	*/
/*@Bean
	@ServiceActivator(inputChannel = "outbound")
	public JobLaunchingGateway jobLaunchingGateway(JobLauncher jobLauncher){
		JobLaunchingGateway jg = new JobLaunchingGateway(jobLauncher);
		jg.setOutputChannel(loggingChannel());
		return jg;
	}*//*



	@Bean
	@ServiceActivator(inputChannel = "outbound", outputChannel = "loggingChannel")
	protected JobLaunchingMessageHandler launcher(JobLauncher jobLauncher) {
		return new JobLaunchingMessageHandler(jobLauncher);
	}


}
*/
