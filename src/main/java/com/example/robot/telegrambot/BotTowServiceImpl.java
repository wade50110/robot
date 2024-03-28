package com.example.robot.telegrambot;

import com.example.robot.config.BotConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class BotTowServiceImpl extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    public String botUsername;

    @Value("${telegram.bot.token}")
    public String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = null;
        Long chatId = null;
        String text = null;

        if (update.hasChannelPost()) {
            message = update.getChannelPost();
        } else {
            message = update.getMessage();
        }

        chatId = message.getChatId();
        text = message.getText();

        this.sendDummyMessage(chatId);
    }


    public void sendDummyMessage(Long chatId) {
        SendMessage sm = new SendMessage();
        sm.setChatId(chatId);
        sm.setText("OKOK!");

        try {
            this.execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

}
