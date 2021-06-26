package com.nklimkin.telegram.bot.repository;

import com.nklimkin.telegram.bot.command.AbstractCommandTest;
import com.nklimkin.telegram.bot.repository.entity.GroupSub;
import com.nklimkin.telegram.bot.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GroupSubRepositoryTest {

    @Autowired
    private GroupSubRepository repository;

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/addTestData.sql"})
    @Test
    void getUsersForGroup() {

        Optional<GroupSub> group = repository.findById(1);

        Assertions.assertTrue(group.isPresent());

        List<TelegramUser> users = group.get().getUsers();

        for (int i = 0; i < users.size(); i++) {
            Assertions.assertEquals(String.valueOf(i + 1), users.get(i).getChatId());
            Assertions.assertTrue(users.get(i).isActive());
        }
    }
}