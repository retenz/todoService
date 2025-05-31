package com.shortcut.todo.dao;

import com.shortcut.todo.entity.ClsTaskStatus;
import com.shortcut.todo.entity.TodoTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoTaskDao extends PagingAndSortingRepository<TodoTask, Long>, CrudRepository<TodoTask, Long> {
    TodoTask findByName(String name);
    Page<TodoTask> findAll(Pageable pageRequest);
    Page<TodoTask> findAllByClsTaskStatus(ClsTaskStatus taskStatus, Pageable pageRequest);
    boolean existsById(Long id);
}
