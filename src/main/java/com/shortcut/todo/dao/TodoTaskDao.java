package com.shortcut.todo.dao;

import com.shortcut.todo.entity.TodoTask;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoTaskDao extends CrudRepository<TodoTask, Long> {
    TodoTask findByName(String name);
    List<TodoTask> findAll(Pageable pageRequest);
    List<TodoTask> findAllByClsTaskStatusId(Integer id, Pageable pageRequest);
    boolean existsById(Long id);
}
