package com.nklimkin.telegram.bot.service;

import com.nklimkin.telegram.bot.repository.TelegramUserRepository;
import com.nklimkin.telegram.bot.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserServiceImpl implements TelegramUserService{

    private TelegramUserRepository repository;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public TelegramUser save(TelegramUser tu) {
        return repository.save(tu);
    }

    @Override
    public List<TelegramUser> getAllActiveUser() {
        return repository.findAllByActiveTrue();
    }

    @Override
    public Optional<TelegramUser> getByChatId(String chatId) {
        return repository.findById(chatId);
    }
}
