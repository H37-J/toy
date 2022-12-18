package com.hjk.service.naver;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class NaverShopService {

    CSVReader reader = new CSVReader(new FileReader("./src/main/java/com/hjk/service/naver/NaverShopKeyword.csv"));
    CSVWriter writer = new CSVWriter(new FileWriter("./src/main/java/com/hjk/service/naver/NaverShop.csv"));
    WebDriver webDriver;

    public NaverShopService() throws IOException {
    }

    public void run() throws InterruptedException, CsvValidationException, IOException {


        System.setProperty("webdriver.chrome.driver", "./src/main/resources/driver/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--blink-settings=imagesEnabled=false");
        webDriver = new ChromeDriver(options);

        String[] header = {"키워드", "페이지번호", "노출순위", "상품번호", "상품명", "카테고리", "등록일", "찜수", "업체명"};
        writer.writeNext(header);

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            String keyword = nextLine[0];
            for (int page = 1; page <= 10; page++) {
                String NAVER_URL = "https://search.shopping.naver.com/search/all?frm=NVSHATC&origQuery=" + keyword + "&pagingIndex=" + page + "&pagingSize=40&productSet=total&query=" + keyword + "&sort=rel&timestamp=&viewType=list";

                webDriver.get(NAVER_URL);

                for (int i = 0; i < 5; i++) {
                    ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                    Thread.sleep(300);
                }
                Thread.sleep(1000);

                List<WebElement> elements = webDriver.findElements(By.cssSelector(".ad"));
                System.out.println(elements.size());

                int id = 1;
                for (WebElement element : elements) {
                    System.out.println("------------");

                    String productTitle = element.findElement(By.cssSelector(".basicList_link__JLQJf")).getText();
                    //String productImage = element.findElement(By.cssSelector(".thumbnail_thumb_wrap__RbcYO img")).getAttribute("src");
                    String productPrice = element.findElement(By.cssSelector(".price_num__S2p_v")).getText();
                    String productNumber = element.findElement(By.cssSelector(".basicList_link__JLQJf")).getAttribute("data-nclick").split("i:")[1].split(",")[0];
                    String productCategory = element.findElement(By.cssSelector(".basicList_nohover__TJr2_")).getText();
                    String uploadDate = element.findElement(By.cssSelector("span.basicList_etc__LSkN_")).getText().split(" ")[1];
                    //String reviewCount = element.findElement(By.cssSelector("a.basicList_etc__LSkN_ .basicList_num__sfz3h")).Ex
                    String heart = element.findElement(By.cssSelector(".basicList_text__gCaiD .basicList_num__sfz3h")).getText();
                    String mallName = element.findElement(By.cssSelector(".basicList_mall__BC5Xu")).getText();

                    if (mallName.equals("")) {
                        mallName = element.findElement(By.cssSelector(".basicList_mall__BC5Xu img")).getAttribute("alt");
                    }

                    String[] datas = {keyword, Integer.toString(page), Integer.toString(id), productNumber, productTitle, productCategory, uploadDate, heart, mallName};
                    writer.writeNext(datas);

                    System.out.println(productTitle);
                    //System.out.println(productImage);
                    System.out.println(productPrice);
                    System.out.println(productNumber);
                    System.out.println(productCategory);
                    System.out.println(uploadDate);
                    //System.out.println(reviewCount);
                    System.out.println(heart);
                    System.out.println(mallName);

                    id += 1;
                }
            }
        }
        writer.close();
    }
}
