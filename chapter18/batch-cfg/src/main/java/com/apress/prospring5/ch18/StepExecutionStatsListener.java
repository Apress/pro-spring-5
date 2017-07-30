package com.apress.prospring5.ch18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class StepExecutionStatsListener extends StepExecutionListenerSupport {

	public static Logger logger = LoggerFactory.getLogger(StepExecutionStatsListener.class);

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.info("--> Wrote: " + stepExecution.getWriteCount()
				+ " items in step: " + stepExecution.getStepName());
		return null;
	}
}
