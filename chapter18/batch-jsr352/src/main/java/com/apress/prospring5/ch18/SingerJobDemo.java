package com.apress.prospring5.ch18;

import org.springframework.batch.core.jsr.launch.JsrJobOperator;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import java.util.Properties;

public class SingerJobDemo {
    public static void main(String... args) throws Exception {
        JsrJobOperator jobOperator = new JsrJobOperator();

        long executionId = jobOperator.start("singerJob", new Properties());
        JobExecution jobExecution = jobOperator.getJobExecution(executionId);

        waitForJob(jobOperator, jobExecution);
    }

    private static void waitForJob(JsrJobOperator jobOperator, JobExecution jobExecution) {
        BatchStatus batchStatus = jobExecution.getBatchStatus();

        while(true) {
            if(batchStatus == BatchStatus.STOPPED || batchStatus == BatchStatus.COMPLETED
                    || batchStatus == BatchStatus.FAILED) {
                return;
            }

            jobExecution = jobOperator.getJobExecution(jobExecution.getExecutionId());
            batchStatus = jobExecution.getBatchStatus();
        }
    }
}
