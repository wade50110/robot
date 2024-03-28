package com.example.robot.telegrambot;

import jakarta.annotation.Resource;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class Initializer {

    @Resource
    BotTowServiceImpl botTowService;

    @EventListener({ContextClosedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(botTowService);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
