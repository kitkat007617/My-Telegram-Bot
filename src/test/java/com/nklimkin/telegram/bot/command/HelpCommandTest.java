package com.nklimkin.telegram.bot.command;

import static org.junit.jupiter.api.Assertions.*;

class HelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HelpCommand.HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(botMessageService);
    }
}