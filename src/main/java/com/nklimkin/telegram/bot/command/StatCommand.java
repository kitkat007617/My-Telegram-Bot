package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.repository.entity.TelegramUser;
import com.nklimkin.telegram.bot.service.SendBotMessageService;
import com.nklimkin.telegram.bot.service.TelegramUserService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@AllArgsConstructor
public class StatCommand implements Command{

    private SendBotMessageService botMessageService;
    private TelegramUserService telegramUserService;

    public static final String STAT_COMMAND = "Мною пользуются %s человек";


    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        botMessageService.sendMessage(chatId, String.format(STAT_COMMAND, telegramUserService.getAllActiveUser().size()));
    }
}
