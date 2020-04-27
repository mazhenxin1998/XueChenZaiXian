package com.mzx.search.utils;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.URL;

/**
 * @author ZhenXinMa
 * @date 2020/4/27 22:30
 */
@Component
public class HtmlUtil implements Serializable {

    @SneakyThrows
    public static void main(String[] args) {

        /*要解析网页的URL*/
        String url = "https://search.jd.com/Search?keyword=java";
        Document document = Jsoup.parse(new URL(url), 30000);
        //Element element = document.getElementById("J_goodsList");
        //System.out.println(element);
        Elements elements = document.getElementsByTag("img");
        for (Element element : elements) {

            System.out.println(element);
        }


    }


}
