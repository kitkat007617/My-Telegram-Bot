package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.repository.entity.GroupSub;
import com.nklimkin.telegram.bot.repository.entity.TelegramUser;
import com.nklimkin.telegram.bot.service.SendBotMessageService;
import com.nklimkin.telegram.bot.service.TelegramUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ListGroupSubCommandTest {

    @Test
    void getGroupSubList() {
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId("1");
        telegramUser.setActive(true);

        List<GroupSub> groupSubs = new ArrayList<>();
        groupSubs.add(populateGroupSub(1, "g1"));
        groupSubs.add(populateGroupSub(2, "g2"));
        groupSubs.add(populateGroupSub(3, "g3"));
        groupSubs.add(populateGroupSub(4, "g4"));

        telegramUser.setGroups(groupSubs);

        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);

        Mockito.when(telegramUserService.getByChatId(telegramUser.getChatId())).thenReturn(Optional.of(telegramUser));

        ListGroupSubCommand listGroupSubCommand = new ListGroupSubCommand(sendBotMessageService, telegramUserService);

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(Long.valueOf(telegramUser.getChatId()));
        Mockito.when(message.getText()).thenReturn(CommandName.LIST_GROUP_SUB.getCommandName());
        update.setMessage(message);

        String collectedGroups = "Ваши группы : \n" +
                telegramUser.getGroups().stream()
                        .map(group -> "Группа " + group.getTitle() + " , ID группы : " + group.getId() + "\n")
                        .collect(Collectors.joining());

        //when
        listGroupSubCommand.execute(update);

        //then
        Mockito.verify(sendBotMessageService).sendMessage(telegramUser.getChatId(), collectedGroups);

    }

    private GroupSub populateGroupSub(Integer id, String title) {
        GroupSub groupSub = new GroupSub();
        groupSub.setId(id);
        groupSub.setTitle(title);
        return groupSub;
    }
}