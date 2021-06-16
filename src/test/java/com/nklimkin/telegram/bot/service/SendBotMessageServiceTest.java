package com.nklimkin.telegram.bot.service;

import com.nklimkin.telegram.bot.bots.MyTelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.junit.jupiter.api.Assertions.*;

class SendBotMessageServiceTest {

    private SendBotMessageService botMessageService;
    private MyTelegramBot telegramBot;

    @BeforeEach
    public void init() {
        telegramBot = Mockito.mock(MyTelegramBot.class);
        botMessageService = new SendBotMessageServiceImpl(telegramBot);
    }

    @Test
    void sendMessage() throws TelegramApiException {

        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        botMessageService.sendMessage(chatId, message);

        Mockito.verify(telegramBot).execute(sendMessage);
    }
}