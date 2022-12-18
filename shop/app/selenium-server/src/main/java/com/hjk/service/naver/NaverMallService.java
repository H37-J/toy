package com.hjk.service.naver;

import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class NaverMallService {

    CSVWriter writer = new CSVWriter(new FileWriter("./src/main/java/com/hjk/service/naver/NaverMall.csv"));
    private final String NAVER_URL = "https://search.shopping.naver.com/allmall";

    WebDriver webDriver;

    public NaverMallService() throws IOException {
    }

    public void run() throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--blink-settings=imagesEnabled=false");
        webDriver = new ChromeDriver(options);

        String[] header = {"쇼핑몰명", "몰설명", "카테고리", "상품개수", "몰등급", "스토어찜수", "네이버페이", "네이버페이플러스", "로고이미지"};
        writer.writeNext(header);

        webDriver.get(NAVER_URL);

        for(int i = 0; i < 100; i++) {
            ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(300);
        }
        Thread.sleep(1000);

        List<WebElement> elements = webDriver.findElements(By.cssSelector(".mallList_list_mall__SWWJM li"));

        System.out.println(elements.size());

        for(WebElement element : elements) {
            String shopName = element.findElement(By.cssSelector("a .mallListItem_title__zS_zf")).getText();
            String shopDesc = element.findElement(By.cssSelector("a .mallListItem_text_area__TWa2S p")).getText();
            String naverPay = element.findElements(By.cssSelector("a .mallListItem_title_area__U4K_2 span")).size() == 1 ? "Y" : "N";

            List<WebElement> additionalElements = element.findElements(By.cssSelector("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4"));

            String rate;
            String heart;
            String shopCategory;
            String productCount;

            if (additionalElements.size() == 1) {
                rate = "";
                heart = "";
                shopCategory = element.findElements(By.cssSelector("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4 span")).get(0).getText();
                productCount = element.findElements(By.cssSelector("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4 span")).get(1).getText();
            } else {
                rate = element.findElements(By.cssSelector("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4")).get(0).findElements(By.cssSelector("span")).get(0).getText();
                heart = element.findElements(By.cssSelector("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4")).get(0).findElements(By.cssSelector("span")).size() == 2 ? element.findElements(By.cssSelector("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4")).get(0).findElements(By.cssSelector("span")).get(1).getText() : "";
                shopCategory = element.findElements(By.cssSelector("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4")).get(1).findElements(By.cssSelector("span")).get(0).getText();
                productCount = element.findElements(By.cssSelector("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4")).get(1).findElements(By.cssSelector("span")).get(1).getText();
            }

            String img = element.findElements(By.cssSelector("a .mallListItem_thumbnail__vYrrk")).get(0).findElement(By.cssSelector("img")).getAttribute("src");
            String naverPayPlus = element.findElements(By.cssSelector("a .mallListItem_benefit__XNmre")).size() == 0 ? "N" : "Y";

            String[] datas = {shopName, shopDesc, shopCategory, productCount, rate, heart, naverPay, naverPayPlus, img};

            writer.writeNext(datas);
        }

        writer.close();
    }
}
