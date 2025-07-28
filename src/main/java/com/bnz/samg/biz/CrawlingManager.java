package com.bnz.samg.biz;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
@RequiredArgsConstructor
public class CrawlingManager {

    private final ThreadPoolTaskScheduler scheduler;
    private final CrawlingTask crawlingTask;
    //    private final TelegramNotifier telegramNotifier;
    private final MyTelegramBot myTelegramBot;

    // 실행 중인 스케줄 보관
    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();
    private final Map<String, Integer> retryCounts = new ConcurrentHashMap<>();


    public void registerSites(List<String> sites) {
        for (String site : sites) {
            startSiteScheduler(site);
        }
    }

    private void startSiteScheduler(String siteUrl) {
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(() -> {
            try {
                crawlingTask.execute(siteUrl);
//                telegramNotifier.send("asdfasdfasdf");
                myTelegramBot.send("asdfasdfasdf");
                retryCounts.put(siteUrl, 0); // 성공하면 초기화
            } catch (Exception e) {
                int retry = retryCounts.getOrDefault(siteUrl, 0) + 1;
                retryCounts.put(siteUrl, retry);

                if (retry % 10 == 0 && retry < 100) {
//                    telegramNotifier.send("[경고] 사이트 크롤링 실패 " + retry + "회: " + siteUrl);
                    myTelegramBot.send("[경고] 사이트 크롤링 실패 " + retry + "회: " + siteUrl);
                }

                if (retry >= 100) {
                    ScheduledFuture<?> task = scheduledTasks.get(siteUrl);
                    if (task != null) task.cancel(true);
                    scheduledTasks.remove(siteUrl);
//                    telegramNotifier.send("🛑 [중단] 100회 실패하여 크롤링을 중단합니다: " + siteUrl);
                    myTelegramBot.send("🛑 [중단] 100회 실패하여 크롤링을 중단합니다: " + siteUrl);
                }
            }
        }, Duration.ofSeconds(5));

        scheduledTasks.put(siteUrl, future);
    }


    private void restartSite(String siteUrl) {
        ScheduledFuture<?> oldTask = scheduledTasks.get(siteUrl);
        if (oldTask != null) {
            oldTask.cancel(true); // 기존 스케줄 종료
        }
        startSiteScheduler(siteUrl); // 재시작
    }

}

