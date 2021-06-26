package com.nklimkin.telegram.bot.service;

import com.nklimkin.telegram.bot.command.AbstractCommandTest;
import com.nklimkin.telegram.bot.javarushclient.dto.GroupDiscussionInfo;
import com.nklimkin.telegram.bot.repository.GroupSubRepository;
import com.nklimkin.telegram.bot.repository.entity.GroupSub;
import com.nklimkin.telegram.bot.repository.entity.TelegramUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GroupSubServiceTest {

    private GroupSubService groupSubService;
    private GroupSubRepository groupSubRepository;
    private TelegramUser telegramUser;

    private static String CHAT_ID = "1";

    @BeforeEach
    void init() {
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        this.groupSubRepository = Mockito.mock(GroupSubRepository.class);
        this.groupSubService = new GroupSubServiceImpl(groupSubRepository, telegramUserService);

        telegramUser = new TelegramUser();
        telegramUser.setChatId(CHAT_ID);
        telegramUser.setActive(true);

        Mockito.when(telegramUserService.getByChatId(CHAT_ID)).thenReturn(Optional.of(telegramUser));
    }

    @Test
    void saveNewGroup() {
        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setTitle("g1");
        expectedGroupSub.setId(1);
        expectedGroupSub.addUser(telegramUser);

        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }

    @Test
    void addNewUserToGroup() {
        TelegramUser oldTelegramUser = new TelegramUser();
        oldTelegramUser.setChatId("2");
        oldTelegramUser.setActive(false);

        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");

        GroupSub groupSubFromDB = new GroupSub();
        groupSubFromDB.setTitle(groupDiscussionInfo.getTitle());
        groupSubFromDB.setId(groupDiscussionInfo.getId());
        groupSubFromDB.addUser(oldTelegramUser);

        Mockito.when(groupSubRepository.findById(groupDiscussionInfo.getId())).thenReturn(Optional.of(groupSubFromDB));

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(oldTelegramUser);
        expectedGroupSub.addUser(telegramUser);

        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        Mockito.verify(groupSubRepository).findById(groupDiscussionInfo.getId());
        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }

}