package com.nklimkin.telegram.bot.command;

import com.google.common.collect.ImmutableMap;
import com.nklimkin.telegram.bot.service.SendBotMessageService;
import org.springframework.stereotype.Component;

@Component
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService botMessageService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandName.START.getCommandName(), new StartCommand(botMessageService))
                .put(CommandName.STOP.getCommandName(), new StopCommand(botMessageService))
                .put(CommandName.HELP.getCommandName(), new HelpCommand(botMessageService))
                .put(CommandName.NO.getCommandName(), new NoCommand(botMessageService))
                .build();

        unknownCommand = new UnknownCommand(botMessageService);

    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
