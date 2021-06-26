package com.nklimkin.telegram.bot.javarushclient;

import com.nklimkin.telegram.bot.javarushclient.dto.GroupFilter;
import com.nklimkin.telegram.bot.javarushclient.dto.GroupInfoType;
import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Builder
public class GroupCountRequestArgument {

    private final String query;
    private final GroupInfoType type;
    private final GroupFilter filter;

    public Map populateQueries() {
        Map queries = new HashMap<>();
        if (nonNull(query))
            queries.put("query", query);
        if (nonNull(type))
            queries.put("type", type);
        if (nonNull(filter))
            queries.put("filter", filter);
        return queries;
    }
}
