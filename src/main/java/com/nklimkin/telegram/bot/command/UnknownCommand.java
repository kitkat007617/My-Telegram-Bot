package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command {

    private SendBotMessageService botMessageService;

    public static final String UNKNOWN_COMMAND_MESSAGE = "Я такое не умею, если хотите посмотреть чего я умею напишите /help";

    public UnknownCommand(SendBotMessageService botMessageService) {
        this.botMessageService = botMessageService;
    }

    @Override
    public void execute(Update update) {
        botMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_COMMAND_MESSAGE);
    }
}
