package com.nklimkin.telegram.bot.service;

import com.nklimkin.telegram.bot.javarushclient.dto.GroupDiscussionInfo;
import com.nklimkin.telegram.bot.repository.GroupSubRepository;
import com.nklimkin.telegram.bot.repository.entity.GroupSub;
import com.nklimkin.telegram.bot.repository.entity.TelegramUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupSubServiceImpl implements GroupSubService {

    private final GroupSubRepository groupSubRepository;
    private final TelegramUserService telegramUserService;

    @Override
    public GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo) {

        TelegramUser telegramUser = telegramUserService.getByChatId(chatId).orElseThrow(NotFoundException::new);

        GroupSub groupSub;
        Optional<GroupSub> groupSubFromDB = groupSubRepository.findById(groupDiscussionInfo.getId());
        if (groupSubFromDB.isPresent()) {
            groupSub = groupSubFromDB.get();
            Optional<TelegramUser> first = groupSub.getUsers().stream()
                    .filter(tu -> tu.getChatId().equals(chatId))
                    .findFirst();
            if (first.isEmpty()) {
                groupSub.addUser(telegramUser);
            }
        } else {
            groupSub = new GroupSub();
            groupSub.setId(groupDiscussionInfo.getId());
            groupSub.setTitle(groupDiscussionInfo.getTitle());
            groupSub.addUser(telegramUser);
        }
        return groupSubRepository.save(groupSub);
    }
}
