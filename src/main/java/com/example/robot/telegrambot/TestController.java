package com.example.robot.telegrambot;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@RestController
public class TestController {

    @Resource
    private TelegramBotsApi telegramBotsApi;
    @Resource
    private DefaultBotOptions defaultBotOptions;
    @Resource
    private LeoRobot botTowService;

    @GetMapping("test")
    public String test() {
        try {
            telegramBotsApi.registerBot(botTowService);
        } catch (Exception e) {
            return "Error";
        }
        return "OK";
    }
}
