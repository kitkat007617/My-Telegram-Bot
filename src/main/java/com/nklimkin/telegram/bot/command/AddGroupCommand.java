package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.javarushclient.GroupRequestArgument;
import com.nklimkin.telegram.bot.javarushclient.JavaRushGroupClient;
import com.nklimkin.telegram.bot.javarushclient.dto.GroupDiscussionInfo;
import com.nklimkin.telegram.bot.repository.entity.GroupSub;
import com.nklimkin.telegram.bot.service.GroupSubService;
import com.nklimkin.telegram.bot.service.SendBotMessageService;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class AddGroupCommand implements Command{

    private final SendBotMessageService botMessageService;
    private final GroupSubService subService;
    private final JavaRushGroupClient javaRushGroupClient;

    public AddGroupCommand(SendBotMessageService botMessageService, GroupSubService subService, JavaRushGroupClient javaRushGroupClient) {
        this.botMessageService = botMessageService;
        this.subService = subService;
        this.javaRushGroupClient = javaRushGroupClient;
    }

    private void sendGroupNotFound(String chatId, String groupId) {
        String groupNotFoundMessage = "Нет группы с id=" + groupId;
        botMessageService.sendMessage(chatId, groupNotFoundMessage);
    }

    private void sendGroupIdList(String chatId) {
        String groupIds = javaRushGroupClient.getGroupList(GroupRequestArgument.builder().build())
                .stream().map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        String message = "Чтобы подписаться на группу - передай комадну вместе с ID группы. \n" +
                "Например: /addGroupSub 16. \n\n" +
                "я подготовил список всех групп - выберай какую хочешь :) \n\n" +
                "имя группы - ID группы \n\n" +
                "%s";

        botMessageService.sendMessage(chatId, String.format(message, groupIds));
    }

    @Override
    public void execute(Update update) {
        String message = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        if (message.equalsIgnoreCase(CommandName.ADD_GROUP_SUB.getCommandName())){
            sendGroupIdList(chatId);
            return;
        }
        String groupId = message.split(" ")[1];
        if (StringUtils.isNumeric(groupId)) {
            GroupDiscussionInfo groupDiscussionInfo = javaRushGroupClient.getGroupById(Integer.parseInt(groupId));
            if (isNull(groupDiscussionInfo.getId())) {
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub savedGroupSub = subService.save(chatId, groupDiscussionInfo);
            botMessageService.sendMessage(chatId, "Подписал на группу " + savedGroupSub.getTitle());
        } else {
            sendGroupNotFound(chatId, groupId);
        }
    }
}
