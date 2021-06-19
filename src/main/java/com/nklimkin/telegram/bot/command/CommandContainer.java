package com.nklimkin.telegram.bot.command;

import com.google.common.collect.ImmutableMap;
import com.nklimkin.telegram.bot.service.SendBotMessageService;
import com.nklimkin.telegram.bot.service.TelegramUserService;
import org.springframework.stereotype.Component;

@Component
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService botMessageService, TelegramUserService telegramUserService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandName.START.getCommandName(), new StartCommand(botMessageService, telegramUserService))
                .put(CommandName.STOP.getCommandName(), new StopCommand(botMessageService, telegramUserService))
                .put(CommandName.STAT.getCommandName(), new StatCommand(botMessageService, telegramUserService))
                .put(CommandName.HELP.getCommandName(), new HelpCommand(botMessageService))
                .put(CommandName.NO.getCommandName(), new NoCommand(botMessageService))
                .build();

        unknownCommand = new UnknownCommand(botMessageService);

    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
