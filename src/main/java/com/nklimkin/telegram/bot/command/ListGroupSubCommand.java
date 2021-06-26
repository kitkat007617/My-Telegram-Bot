package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.repository.entity.TelegramUser;
import com.nklimkin.telegram.bot.service.GroupSubService;
import com.nklimkin.telegram.bot.service.SendBotMessageService;
import com.nklimkin.telegram.bot.service.TelegramUserService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.ws.rs.NotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ListGroupSubCommand implements Command {

    private SendBotMessageService sendBotMessageService;
    private TelegramUserService telegramUserService;


    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        TelegramUser user = telegramUserService.getByChatId(chatId).orElseThrow(NotFoundException::new);
        String groups = user.getGroups().stream()
                .map(group -> "Группа " + group.getTitle() + " , ID группы : " + group.getId() + "\n")
                .collect(Collectors.joining());
        sendBotMessageService.sendMessage(chatId, String.format("Ваши группы : \n%s", groups));
    }
}
