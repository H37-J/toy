package com.hjk.user;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;

import java.io.IOException;
import java.io.Writer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.hjk.common.CustomJobListener;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UserJobConfig {
    

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final CustomJobListener csvBatchListener;

    private final Integer CHUNK_SIZE = 10;
    private final Integer PAGE_SIZE=10;
}
