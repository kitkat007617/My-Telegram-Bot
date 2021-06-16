package com.nklimkin.telegram.bot.command;

import static org.junit.jupiter.api.Assertions.*;

class StopCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return "/stop";
    }

    @Override
    String getCommandMessage() {
        return CommandName.STOP.getCommandName();
    }

    @Override
    Command getCommand() {
        return new StopCommand(botMessageService);
    }
}