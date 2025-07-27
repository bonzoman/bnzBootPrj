package com.bnz.samg.biz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrawlingTask {
//    private final MyBatisService dbService;
//    private final TelegramService telegram;

    public void execute(String symbol) {
//        try {
//            String price = crawlPrice(symbol);
//            dbService.savePrice(symbol, price);
//            telegram.sendMessage("📈 " + symbol + " 가격: " + price);
//        } catch (Exception e) {
//            telegram.sendMessage("❌ 크롤링 실패: " + symbol + " - " + e.getMessage());
//        }
    }

    private String crawlPrice(String symbol) {
        // 간단한 예시 (실제는 Jsoup이나 Selenium 사용)
        return Math.round(Math.random() * 100000) + "원";
    }
}
