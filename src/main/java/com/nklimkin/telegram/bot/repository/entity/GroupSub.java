package com.nklimkin.telegram.bot.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Data
@Entity
@Table(name = "group_sub")
@EqualsAndHashCode
public class GroupSub {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "last_article_id")
    private int lastArticleId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_x_user",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<TelegramUser> users;

    public void addUser(TelegramUser user) {
        if (isNull(users))
            users = new ArrayList<>();
        users.add(user);
    }

}
