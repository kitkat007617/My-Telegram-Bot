package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements Command {

    private SendBotMessageService messageService;

    public static final String HELP_MESSAGE = String.format("<b>Доступные команды</b>\n\n" +
            "Начать работу с ботом - %s\n" +
            "Приостоновить работу с ботом - %s\n" +
            "Получить помощь в работе - %s\n" +
                    "Посмотреть статистику по боту - %s", CommandName.START.getCommandName(), CommandName.STOP.getCommandName(),
            CommandName.HELP.getCommandName(), CommandName.STAT.getCommandName());

    public HelpCommand(SendBotMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute(Update update) {
        messageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
