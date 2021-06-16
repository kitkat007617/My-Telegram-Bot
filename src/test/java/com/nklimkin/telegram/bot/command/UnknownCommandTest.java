package com.nklimkin.telegram.bot.command;

import static org.junit.jupiter.api.Assertions.*;

class UnknownCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return "/bbrdcec";
    }

    @Override
    String getCommandMessage() {
        return UnknownCommand.UNKNOWN_COMMAND_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(botMessageService);
    }
}