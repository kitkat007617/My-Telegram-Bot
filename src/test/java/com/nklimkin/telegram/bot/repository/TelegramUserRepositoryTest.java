package com.nklimkin.telegram.bot.repository;

import com.nklimkin.telegram.bot.command.AbstractCommandTest;
import com.nklimkin.telegram.bot.repository.entity.GroupSub;
import com.nklimkin.telegram.bot.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TelegramUserRepositoryTest {

    @Autowired
    private TelegramUserRepository repository;

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegram_users.sql"})
    @Test
    void getAllActiveUser() {

        List<TelegramUser> activeUsers = repository.findAllByActiveTrue();

        Assertions.assertEquals(5, activeUsers.size());
    }

    @Sql(scripts = {"/sql/clearDbs.sql" ,"/sql/addTestData.sql"})
    @Test
    void getAllGroupsForUser() {

        Optional<TelegramUser> user = repository.findById("1");

        Assertions.assertTrue(user.isPresent());

        List<GroupSub> groupsByUser = user.get().getGroups();

        for (int i = 0; i < groupsByUser.size(); i++) {
            Assertions.assertEquals(String.format("g%s", (i + 1)), groupsByUser.get(i).getTitle());
            Assertions.assertEquals(i + 1, groupsByUser.get(i).getId());
            Assertions.assertEquals(i + 1, groupsByUser.get(i).getLastArticleId());
        }
    }

}