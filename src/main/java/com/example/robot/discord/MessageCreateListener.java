package com.example.robot.discord;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListener extends MessageListener implements EventListener<MessageCreateEvent>{
    @Override

    public Class<MessageCreateEvent> getEventType() {

        return MessageCreateEvent.class;

    }


    @Override

    public Mono<Void> execute(MessageCreateEvent event) {

        return processCommand(event.getMessage());

    }



}
