package com.nklimkin.telegram.bot.service;

import com.nklimkin.telegram.bot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {

    TelegramUser save(TelegramUser tu);

    List<TelegramUser> getAllActiveUser();

    Optional<TelegramUser> getByChatId(String chatId);
}
