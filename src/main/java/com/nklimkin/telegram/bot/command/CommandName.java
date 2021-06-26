package com.nklimkin.telegram.bot.command;

public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO("no_command"),
    ADD_GROUP_SUB("/addgroupsub"),
    LIST_GROUP_SUB("/listgroupsub"),
    STAT("/stat");

    private String commandName;

    CommandName(String command) {
        this.commandName = command;
    }

    public String getCommandName() {
        return commandName;
    }
}
