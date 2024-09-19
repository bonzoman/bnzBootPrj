package com.bnz;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * SpringBoot 구동 시 이벤트 Listener
 */
//@Component //의미없음
//( ApplicationStartingEvent 는 SpringContext 생성 전 발생하는 이벤트 이므로 @Component 등록 해도 안됨)
//  Application main에서 addLister로 해야함)
public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {
        System.out.println("===========================");
        System.out.println("SampleListener Call !!!!!!! (ApplicationStartingEvent)");
        System.out.println("===========================");


    }


}
