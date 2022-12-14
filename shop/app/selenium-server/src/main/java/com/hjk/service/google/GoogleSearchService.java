package com.hjk.service.google;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GoogleSearchService {

    CSVReader reader = new CSVReader(new FileReader("./src/main/java/com/hjk/service/google/GoogleKeyword.csv"));

    CSVWriter writer = new CSVWriter(new FileWriter("./src/main/java/com/hjk/service/google/GoogleSearch.csv"));

    public GoogleSearchService() throws IOException {
    }

    public void run() throws IOException, CsvValidationException {
        String[] header = {"키워드", "노출순서", "표시 URL", "제목", "설명"};
        writer.writeNext(header);

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            String keyword = nextLine[0];
            String GOOGLE_URL = "https://www.google.com/search?q=" + keyword;

            Document doc = Jsoup.connect(GOOGLE_URL).get();
            Elements contents = doc.select("#taw .qGXjvb .uEierd");

            System.out.println(keyword);
            System.out.println(contents.size());

            if(contents.size() == 0) {
                String[] nullData = {keyword, "", "", "", ""};
                writer.writeNext(nullData);
            }

            int id = 1;
            for(Element content : contents) {
                String num = String.valueOf(id);
                String link = content.select(".vdQmEd .v5yQqb a").attr("href");
                String title = content.select(".vdQmEd .v5yQqb a div div span").text();
                String desc = content.select(".vdQmEd .MUxGbd").text();

                String[] datas = {keyword, num, link, title, desc};

                writer.writeNext(datas);
                id += 1;
            }
        }



        writer.close();
    }
}
