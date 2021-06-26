package com.nklimkin.telegram.bot.javarushclient;

import com.nklimkin.telegram.bot.javarushclient.dto.GroupDiscussionInfo;
import com.nklimkin.telegram.bot.javarushclient.dto.GroupInfo;

import java.util.List;

public interface JavaRushGroupClient {

    List<GroupInfo> getGroupList(GroupRequestArgument requestArgument);

    List<GroupDiscussionInfo> getGroupDiscussionInfo(GroupRequestArgument requestArgument);

    Integer getGroupCount(GroupCountRequestArgument requestArgument);

    GroupDiscussionInfo getGroupById(Integer id);
}
