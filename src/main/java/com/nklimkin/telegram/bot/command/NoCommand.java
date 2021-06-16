package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {

    private SendBotMessageService botMessageService;

    public static final String NO_COMMAND_MESSAGE = "Моя твоя не понимать, введите команду начинающуюся со слеша(/). " +
            "Хотите посмотреть мои команды введите /help";

    public NoCommand(SendBotMessageService botMessageService) {
        this.botMessageService = botMessageService;
    }

    @Override
    public void execute(Update update) {
        botMessageService.sendMessage(update.getMessage().getChatId().toString(), NO_COMMAND_MESSAGE);
    }
}
