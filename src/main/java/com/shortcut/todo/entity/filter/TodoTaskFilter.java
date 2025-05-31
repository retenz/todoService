package com.shortcut.todo.entity.filter;

import com.shortcut.todo.entity.ClsTaskStatus;
import lombok.Data;

@Data
public class TodoTaskFilter {
    private boolean sortByDate;
    private boolean sortByCreateDate;
    private ClsTaskStatus taskStatus;

    private Integer page = 0;
    private Integer size = 10;
}
