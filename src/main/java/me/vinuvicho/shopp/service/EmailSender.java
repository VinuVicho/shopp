package me.vinuvicho.shopp.service;

import org.springframework.scheduling.annotation.Async;

public interface EmailSender {
    @Async
    void send(String to, String email);
}
