package com.nklimkin.telegram.bot.command;

import static org.junit.jupiter.api.Assertions.*;

class NoCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NoCommand.NO_COMMAND_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(botMessageService);
    }
}