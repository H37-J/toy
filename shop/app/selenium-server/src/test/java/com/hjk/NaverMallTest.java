package com.hjk;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NaverMallTest {

    WebDriver webDriver;

    @Test
    public void test() {

        System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver");
        webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.get("https://google.com");
    }
}
