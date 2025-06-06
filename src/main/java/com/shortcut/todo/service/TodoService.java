package com.shortcut.todo.service;

import com.shortcut.todo.dao.TodoTaskDao;
import com.shortcut.todo.entity.ClsTaskStatus;
import com.shortcut.todo.entity.TodoTask;
import com.shortcut.todo.entity.filter.TodoTaskFilter;
import com.shortcut.todo.exception.ServiceException;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Log4j2
public class TodoService {
    @Resource private TodoTaskDao todoTaskDao;

    public TodoTask createTodoTask(TodoTask todoTask) {
        log.info("Начало создания задачи");
        todoTask.setCreateDate(LocalDateTime.now());
        return todoTaskDao.save(todoTask);
    }

    public Optional<TodoTask> getTodoTaskById(Long id) {
        if (id == null) {
            log.warn("Задачи с id = {} не найдено", id);
            throw new IllegalArgumentException("ID задачи не может быть null");
        }

        log.info("Получение задачи по id = {}", id);
        return todoTaskDao.findById(id);
    }

    public Page<TodoTask> getTodoTasks(TodoTaskFilter todoTaskFilter) {
        Page<TodoTask> todoTasks;
        Pageable pageRequest = buildPageRequest(todoTaskFilter);
        ClsTaskStatus taskStatus = todoTaskFilter.getTaskStatus();

        if (taskStatus != null) {
            log.info("Получение списка задач с statusId = {} name = {}", taskStatus.getId(), taskStatus.getName());
            todoTasks = todoTaskDao.findAllByClsTaskStatus(taskStatus, pageRequest);
        } else {
            log.info("Получение всего списка задач");
            todoTasks = todoTaskDao.findAll(pageRequest);
        }
        return todoTasks;
    }

    public TodoTask updateTodoTask(TodoTask todoTask) throws ServiceException {
        if (todoTask.getId() == null) {
            log.warn("ID задачи не может быть null");
            throw new ServiceException("ID задачи не может быть null");
        }

        if (!todoTaskDao.existsById(todoTask.getId())) {
            log.warn("Задачи с id = {} не найдено", todoTask.getId());
            throw new ServiceException(String.format("Задачи с id = %s не найдено", todoTask.getId()));
        }

        log.info("Обновление задачи с id = {}", todoTask.getId());
        todoTask.setUpdateDate(LocalDateTime.now());
        return todoTaskDao.save(todoTask);
    }

    public void deleteTodoTaskById(Long id) throws ServiceException {
        if (id == null) {
            log.warn("ID задачи не может быть null");
            throw new ServiceException("ID задачи не может быть null");
        }

        if (!todoTaskDao.existsById(id)) {
            log.warn("Задачи с id = {} не найдено", id);
            throw new ServiceException(String.format("Задачи с id = %s не найдено", id));
        }

        log.info("Удаление задачи с id = {}", id);
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
