package com.example.robot.config;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Data
@Configuration
@ConfigurationProperties(prefix = "telegram.bot")
public class BotConfig {
    private String username;
    private String token;
    private String host;
    private String port;

    @Bean
    public DefaultBotOptions getDefaultBotOptions(){
        DefaultBotOptions options = new DefaultBotOptions();
        options.setProxyHost(host);
        options.setProxyPort(Integer.parseInt(port));
        options.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        return options;
    }

    public DefaultBotSession DefaultBotSession(){
        DefaultBotSession defaultBotSession = new DefaultBotSession();
        defaultBotSession.setOptions(getDefaultBotOptions());

        return defaultBotSession;
    }

    @SneakyThrows
    @Bean
    public TelegramBotsApi telegramBotsApi(){
        return new TelegramBotsApi(DefaultBotSession().getClass());
    }
}
