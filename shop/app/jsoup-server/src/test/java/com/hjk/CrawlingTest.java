package com.hjk;

import com.hjk.service.google.GoogleSearchService;
import com.hjk.service.NaverMall.NaverMallService;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlingTest {

    @Autowired
    private NaverMallService naverMallService;

    @Autowired
    private GoogleSearchService googleSearchService;

    @Test
    public void NaverServiceTest() throws IOException {
        naverMallService.test();
    }

    @Test
    public void GoogleServiceTest() throws IOException, CsvValidationException {
        googleSearchService.test();
    }

}
