package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.bots.MyTelegramBot;
import com.nklimkin.telegram.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    private SendBotMessageService botMessageService;

    public final static String START_MESSAGE = "Привет, я еще бесполезный телеграмм бот, давай танцевать!";

    public StartCommand(SendBotMessageService botMessageService) {
        this.botMessageService = botMessageService;
    }

    @Override
    public void execute(Update update) {
        botMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
