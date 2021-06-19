package com.nklimkin.telegram.bot.command;

import com.nklimkin.telegram.bot.bots.MyTelegramBot;
import com.nklimkin.telegram.bot.repository.entity.TelegramUser;
import com.nklimkin.telegram.bot.service.SendBotMessageService;
import com.nklimkin.telegram.bot.service.TelegramUserService;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.Update;

@AllArgsConstructor
public class StartCommand implements Command {

    private SendBotMessageService botMessageService;
    private TelegramUserService telegramUserService;

    public final static String START_MESSAGE = "Привет, я еще бесполезный телеграмм бот, давай танцевать!";

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        telegramUserService.getByChatId(chatId).ifPresentOrElse(user -> {
            user.setActive(true);
            telegramUserService.save(user);
        },
        () -> {
            TelegramUser newUser = new TelegramUser();
            newUser.setChatId(chatId);
            newUser.setActive(true);
            telegramUserService.save(newUser);
        });

        botMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
