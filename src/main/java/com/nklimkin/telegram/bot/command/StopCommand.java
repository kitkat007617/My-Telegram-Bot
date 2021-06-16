package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {

    private SendBotMessageService botMessageService;

    public static final String STOP_MESSAGE = "Пока, брат";

    public StopCommand(SendBotMessageService botMessageService) {
        this.botMessageService = botMessageService;
    }

    @Override
    public void execute(Update update) {
        botMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
    }
}
