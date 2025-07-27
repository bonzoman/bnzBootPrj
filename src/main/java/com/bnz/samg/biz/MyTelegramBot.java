package com.bnz.samg.biz;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    // 테스트용 채팅 ID (원하시는 실제 채팅 ID로 바꿔주세요)
    private static final String TEST_CHAT_ID = "-1001163615391";
    @Value("${telegram.bot.username}")
    private String botUsername;
    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // 이 예제에선 수신 처리는 하지 않습니다.
    }

    // 애플리케이션 기동 후 자동으로 테스트 메시지 전송
    @PostConstruct
    public void sendTestMessage() {
        SendMessage msg = SendMessage.builder()
                .chatId(TEST_CHAT_ID)
                .text("Spring Boot + Java21 에서 보낸 첫 메시지 🎉")
                .build();
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
