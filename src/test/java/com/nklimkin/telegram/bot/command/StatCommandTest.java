package com.nklimkin.telegram.bot.command;

public class StatCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return CommandName.STAT.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(StatCommand.STAT_COMMAND, 0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(botMessageService, userService);
    }
}
