package com.nklimkin.telegram.bot.javarushclient;

import com.nklimkin.telegram.bot.javarushclient.dto.GroupDiscussionInfo;
import com.nklimkin.telegram.bot.javarushclient.dto.GroupInfo;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JavaRushGroupClientImpl implements JavaRushGroupClient {

    private final String javarushApiGroupPath;

    public JavaRushGroupClientImpl(@Value("${javarush.api.path}") String javarushApi) {
        this.javarushApiGroupPath = javarushApi + "/groups";
    }

    @Override
    public List<GroupInfo> getGroupList(GroupRequestArgument requestArgument) {
        return Unirest.get(javarushApiGroupPath).queryString(requestArgument.populateQueries())
                .asObject(new GenericType<List<GroupInfo>>() {
                }).getBody();
    }

    @Override
    public List<GroupDiscussionInfo> getGroupDiscussionInfo(GroupRequestArgument requestArgument) {
        return Unirest.get(javarushApiGroupPath).queryString(requestArgument.populateQueries())
                .asObject(new GenericType<List<GroupDiscussionInfo>>() {
                }).getBody();
    }

    @Override
    public Integer getGroupCount(GroupCountRequestArgument requestArgument) {
        return Integer.valueOf(Unirest.get(javarushApiGroupPath + "/count")
                .queryString(requestArgument.populateQueries())
                .asString()
                .getBody());
    }

    @Override
    public GroupDiscussionInfo getGroupById(Integer id) {
        return Unirest.get(javarushApiGroupPath + "/group" + id)
                .asObject(new GenericType<GroupDiscussionInfo>() {})
                .getBody();
    }
}
