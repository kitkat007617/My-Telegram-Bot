package com.nklimkin.telegram.bot.command;

import static org.junit.jupiter.api.Assertions.*;

class StartCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return StartCommand.START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(botMessageService);
    }
}