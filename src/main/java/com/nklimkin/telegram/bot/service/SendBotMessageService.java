package com.nklimkin.telegram.bot.service;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
}
