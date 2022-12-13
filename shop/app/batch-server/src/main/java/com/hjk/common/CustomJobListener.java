package com.hjk.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomJobListener implements JobExecutionListener {

    
    @Override
    public void beforeJob(JobExecution jobExecution){
        log.info("##### CsvBatchJob 시작 시간 : {}", jobExecution.getStartTime());
    }

    @Override
    public void afterJob(JobExecution jobExecution){
        log.info("##### CsvBatchJob 종료 시간 : {}", jobExecution.getEndTime());
    }
}

