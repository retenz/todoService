package com.shortcut.todo.service;

import com.shortcut.todo.dao.TodoTaskDao;
import com.shortcut.todo.entity.TodoTask;
import com.shortcut.todo.entity.filter.TodoTaskFilter;
import com.shortcut.todo.exception.ServiceException;
import jakarta.annotation.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    @Resource private TodoTaskDao todoTaskDao;

    public TodoTask createTodoTask(TodoTask todoTask) {
        todoTask.setCreateDate(LocalDateTime.now());
        return todoTaskDao.save(todoTask);
    }

    public TodoTask getTodoTaskById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID задачи не может быть null");
        }

        return todoTaskDao.findById(id).orElse(null);
    }

    public List<TodoTask> getTodoTasks(TodoTaskFilter todoTaskFilter) {
        List<TodoTask> todoTasks;
        Pageable pageRequest = buildPageRequest(todoTaskFilter);

        if (todoTaskFilter.getTaskStatusId() != null) {
            todoTasks = todoTaskDao.findAllByClsTaskStatusId(todoTaskFilter.getTaskStatusId(), pageRequest);
        } else {
            todoTasks = todoTaskDao.findAll(pageRequest);
        }
        return todoTasks;
    }

    public TodoTask updateTodoTask(TodoTask todoTask) throws ServiceException {
        if (todoTask.getId() == null) {
            throw new ServiceException("ID задачи не может быть null");
        }

        if (!todoTaskDao.existsById(todoTask.getId())) {
            throw new ServiceException(String.format("Задачи с id = %s не найдено", todoTask.getId()));
        }

        todoTask.setUpdateDate(LocalDateTime.now());
        return todoTaskDao.save(todoTask);
    }

    public void deleteTodoTaskById(Long id) throws ServiceException {
        if (id == null) {
            throw new ServiceException("ID задачи не может быть null");
        }

        if (!todoTaskDao.existsById(id)) {
            throw new ServiceException(String.format("Задачи с id = %s не найдено", id));
        }

        todoTaskDao.deleteById(id);
    }

    private Pageable buildPageRequest(TodoTaskFilter todoTaskFilter) {
        Sort sort = Sort.unsorted();
        if (todoTaskFilter.isSortByDate()) {
            sort = sort.and(Sort.by(Sort.Direction.ASC, "updateDate"));
        }
        if (todoTaskFilter.isSortByCreateDate()) {
            sort = sort.and(Sort.by(Sort.Direction.ASC, "createDate"));
        }

        return PageRequest.of(todoTaskFilter.getPage(), todoTaskFilter.getSize(), sort);
    }
}
