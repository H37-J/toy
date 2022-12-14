package com.hjk.service.NaverMall;

import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class NaverMallService {
    CSVWriter writer = new CSVWriter(new FileWriter("./src/main/java/com/hjk/service/NaverMall/NaverMall.csv"));
    private final String NAVER_URL = "https://search.shopping.naver.com/allmall";

    public NaverMallService() throws IOException {
    }

    public void test() throws IOException {
        Document doc = Jsoup.connect(NAVER_URL).get();
        Elements contents = doc.select(".mallList_list_mall__SWWJM li");

        String[] header = {"쇼핑몰명", "몰설명", "카테고리", "상품개수", "몰등급", "스토어찜수", "네이버페이", "네이버페이플러스", "로고이미지"};
        writer.writeNext(header);

        for(Element content : contents) {
            String shopName = content.select("a .mallListItem_text_area__TWa2S .mallListItem_title_area__U4K_2 strong").text();
            String shopDesc = content.select("a .mallListItem_text_area__TWa2S p").text();
            String naverPay = content.select("a .mallListItem_text_area__TWa2S .mallListItem_title_area__U4K_2 span").text().equals("") ? "N" : "Y";

            Elements additionalContent = content.select("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4");

            String rate;
            String heart;
            String shopCategory;
            String productCount;

            if (additionalContent.size() == 1) {
                rate = "";
                heart = "";
                shopCategory = content.select("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4 span").get(0).text();
                productCount = content.select("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4 span").get(1).text();
            } else {
                rate = content.select("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4").get(0).select("span").get(0).text();
                heart = content.select("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4").get(0).select("span").get(1).text().split(" ")[1];
                shopCategory = content.select("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4").get(1).select("span").get(0).text();
                productCount = content.select("a .mallListItem_text_area__TWa2S .mallListItem_etc__d1uj4").get(1).select("span").get(1).text().split(" ")[1];
            }

            String img = content.select("a .mallListItem_thumbnail__vYrrk").get(0).select("img").attr("src");
            String naverPayPlus = content.select("a .mallListItem_benefit__XNmre").size() == 0 ? "N" : "Y";

            String[] datas = {shopName, shopDesc, shopCategory, productCount, rate, heart, naverPay, naverPayPlus, img};

            writer.writeNext(datas);
        }

        writer.close();

    }
}
