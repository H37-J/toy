package com.hjk;

import com.hjk.service.google.GoogleSearchService;
import com.hjk.service.naver.NaverMallService;
import com.hjk.service.naver.NaverShopService;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class NaverMallTest {

    @Autowired
    private GoogleSearchService googleSearchService;
    @Autowired
    private NaverMallService naverMallService;

    @Autowired
    private NaverShopService naverShopService;

    @Test
    public void googleServiceTest() throws CsvValidationException, IOException, InterruptedException {
        googleSearchService.run();
    }

    @Test
    public void naverMallServiceTest() throws CsvValidationException, IOException, InterruptedException {
        naverMallService.run();
    }

    @Test
    public void NaverShopServiceTest() throws CsvValidationException, IOException, InterruptedException {
        naverShopService.run();
    }
}
