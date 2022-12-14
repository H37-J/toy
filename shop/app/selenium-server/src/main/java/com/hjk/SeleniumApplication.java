package com.hjk;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
public class SeleniumApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SeleniumApplication.class, args);
    }
}
