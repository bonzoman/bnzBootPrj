package com.bnz;

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 웹서버 구동시 Port 알아내기
 */
@Component
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        ServletWebServerApplicationContext app = event.getApplicationContext();
        System.out.println("=========================");
        System.out.println("Port Listener call. Port:"+app.getWebServer().getPort());
        System.out.println("=========================");

    }
}
