package com.bnz.samg.biz;

import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Component
@RequiredArgsConstructor
public class ScheduledCrawler {
    private final TaskExecutor taskExecutor;
    private final ExecutorService executorService;      // 추가 병렬 실행용
    private final CrawlingTask crawlingTask;

    @Scheduled(fixedDelay = 10000) // 10초마다 실행
    public void crawlAllTargets() {
        List<String> targets = List.of("bit", "eth", "xrp");

        for (String target : targets) {
            taskExecutor.execute(() -> crawlingTask.execute(target));
            executorService.submit(() -> crawlingTask.execute(target));
        }
    }
}
