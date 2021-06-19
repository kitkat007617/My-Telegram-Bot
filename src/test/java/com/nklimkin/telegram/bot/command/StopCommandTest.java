package com.nklimkin.telegram.bot.command;

import static org.junit.jupiter.api.Assertions.*;

class StopCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return StopCommand.STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(botMessageService, userService);
    }
}