package com.shortcut.todo.entity.filter;

import lombok.Data;

@Data
public class TodoTaskFilter {
    private Integer taskStatusId;
    private boolean sortByDate;
    private boolean sortByCreateDate;

    private Integer page = 0;
    private Integer size = 10;
}
