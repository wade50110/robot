package com.example.robot.discord;

import discord4j.core.event.domain.Event;
import reactor.core.publisher.Mono;

public interface EventListener<T extends Event> {

    Class<T> getEventType();

    Mono<Void> execute(T event);

    default Mono<Void> handleError(Throwable error) {
        System.out.println( "Unable to process " + getEventType().getSimpleName() + error.getMessage());
        return Mono.empty();
    }

}
