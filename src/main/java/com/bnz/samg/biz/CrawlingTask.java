package com.bnz.samg.biz;

import com.bnz.samg.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrawlingTask {
//    private final MyBatisService dbService;
//    private final TelegramService telegram;

    public void execute(String url) {
        System.out.println("크롤링 시작: " + url);

        if (url.contains("fail")) {
            throw new RuntimeException("강제 실패"); // 테스트용
        }

        try {


            // 여기에 실제 크롤링 + DB 저장 로직 구현
            Document doc = Jsoup.connect(url)
                    .header("Cache-control", "no-cache")
                    .header("Cache-store", "no-store")
                    .header("Pragma", "no-cache")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                    .get();
            //			System.out.println(doc);
            Elements elements = doc.select("li.press_edit_news_item._rcount > a");

            for (int j = 0; j < elements.size(); j++) {
                Element subRow = elements.get(j);
                Element es = subRow.selectFirst("span.press_edit_news_title");
                String title = es.text().trim();
                String link = subRow.attr("href");
                String issue_time = DateUtil.getNowDate("yyyy/MM/dd HH:mm:ss");//"9999/12/31 23:59:59";

                System.out.println(title.replaceAll("[\r\n]+", "").trim() + "▦" + link.trim() + "▦aaaaa▦" + issue_time.trim());
            }

            System.out.println("✅ [" + Thread.currentThread().getName() + "] " + url + " 크롤링 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
