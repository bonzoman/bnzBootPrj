package com.bnz.config;

import com.bnz.samg.biz.CrawlingManager;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CrawlingInitLauncher {

    private final CrawlingManager crawlingManager;

    @PostConstruct
    public void launchCrawling() {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            urls.add("https://media.naver.com/press/032?sid=105");//naver news 경향신문
            urls.add("https://media.naver.com/press/214?sid=105");//naver news MBC
            urls.add("https://media.naver.com/press/092?sid=105");//naver news ZDNet_Korea
        }

        crawlingManager.registerSites(urls);
    }
}
