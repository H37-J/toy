package com.hjk.user;

import javax.persistence.EntityManagerFactory;

import com.hjk.model.User;
import com.hjk.repository.UserRepository;
import com.hjk.service.UserService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.hjk.common.CustomJobListener;

import lombok.RequiredArgsConstructor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class UserJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final EntityManagerFactory entityManagerFactory;

    private final CustomJobListener customJobListener;

    private final Integer CHUNK_SIZE = 10000;

    private final Integer PAGE_SIZE = 10;

    private final Integer CORE_POOL_SIZE = 20;
    private final Integer MAX_POOL_SIZE = 50;
    private final Integer QUEUE_POOL_SIZE = 50;

    private final UserRepository userRepository;
    private final UserService userService;
    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("UserJob")
                .start(step1())
                .build();
    }
    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("UserStep")
                .<User, User>chunk(CHUNK_SIZE)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(asyncTaskExecutor())
                .throttleLimit(CORE_POOL_SIZE)
                .build();
    }

    @Bean
    public ListItemReader<User> reader() {
        List<User> users = new ArrayList<>();
        for(int i = 1; i <= 100000; i++) {
            String email = "test" + i + "gmail.com";
            String name = "테스트" + i;
            User user = User.builder().email(email).password("test1234").name(name).build();
            users.add(user);
        }

        return new ListItemReader<>(users);
    }

    @Bean
    public ItemProcessor<User, User> processor() {
        return new ItemProcessor<User, User>() {
            @Override
            public User process(User user) throws Exception {
                return user;
            }
        };
    }

    @Bean
    public TaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setThreadNamePrefix("-멀티스레드-");
        executor.setQueueCapacity(QUEUE_POOL_SIZE);
        executor.initialize();
        return executor;
    }


    @Bean
    public AsyncItemProcessor<User, User> asyncProcessor() throws Exception {
        AsyncItemProcessor<User, User> asyncItemProcessor = new AsyncItemProcessor<>();
        asyncItemProcessor.setDelegate(processor());
        asyncItemProcessor.setTaskExecutor(asyncTaskExecutor());
        asyncItemProcessor.afterPropertiesSet();
        return asyncItemProcessor;
    }

    @Bean
    public ItemWriter<User> writer() {
        return new ItemWriter<User>() {
            @Override
            public void write(List<? extends User> users) throws Exception {
               userRepository.saveAll(users);
            }
        };
    }

    @Bean
    public AsyncItemWriter<User> asyncItemWriter() throws Exception {
        AsyncItemWriter<User> asyncItemWriter = new AsyncItemWriter<>();
        asyncItemWriter.setDelegate(writer());
        asyncItemWriter.afterPropertiesSet();
        return asyncItemWriter;
    }
}
