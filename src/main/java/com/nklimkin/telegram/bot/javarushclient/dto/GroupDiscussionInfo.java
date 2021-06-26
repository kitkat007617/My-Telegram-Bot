package com.nklimkin.telegram.bot.javarushclient.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GroupDiscussionInfo extends GroupInfo{

    private UserDiscussionInfo userDiscussionInfo;
    private Integer commentsCount;
}
