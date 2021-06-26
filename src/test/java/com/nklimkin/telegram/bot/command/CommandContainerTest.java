package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.javarushclient.JavaRushGroupClient;
import com.nklimkin.telegram.bot.service.GroupSubService;
import com.nklimkin.telegram.bot.service.SendBotMessageService;
import com.nklimkin.telegram.bot.service.SendBotMessageServiceImpl;
import com.nklimkin.telegram.bot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        JavaRushGroupClient javaRushGroupClient = Mockito.mock(JavaRushGroupClient.class);
        GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
        commandContainer = new CommandContainer(sendBotMessageService, telegramUserService,
                javaRushGroupClient, groupSubService);
    }

    @Test
    void shouldGetAllExistCommand() {
        Arrays.stream(CommandName.values()).forEach(commandName -> {
            Command command = commandContainer.retrieveCommand(commandName.getCommandName());
            Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
        });
    }

    @Test
    void shouldReturnUnknownCommand() {
        String unknownCommand = "/jgrjrf";

        Command command = commandContainer.retrieveCommand(unknownCommand);

        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}