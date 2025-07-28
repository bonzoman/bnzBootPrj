package com.bnz.samg.biz;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledCrawler {
//    private final TaskExecutor taskExecutor;
//    private final ExecutorService executorService;      // 추가 병렬 실행용
//    private final CrawlingTask crawlingTask;
//
//    @Scheduled(fixedDelay = 3000) // 3초마다 실행
//    public void crawlAllTargets() {
//        List<String> targets = List.of("bit", "eth", "xrp");
//        for (String target : targets) {
//            taskExecutor.execute(() -> crawlingTask.execute(target)); //Future를 반환하지 않아서 예외 감지 및 재시도 로직이 어려움
//            executorService.submit(() -> crawlingTask.execute(target)); //Future 반환 → 예외 추적/재시도 구현 가능
//        }
//    }
}
