package com.bnz.samg.biz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class TelegramNotifier {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${telegram.bot.token}")
    private String botToken;
    @Value("${telegram.chat.id}")
    private String chatId;

    public void send(String message) {
        String url = "https://api.telegram.org/bot" + botToken + "/sendMessage";
        Map<String, String> params = Map.of(
                "chat_id", chatId,
                "text", message
        );

        try {
            restTemplate.postForEntity(url, params, String.class);
        } catch (Exception e) {
            System.err.println("Telegram 알림 실패: " + e.getMessage());
        }
    }
}
