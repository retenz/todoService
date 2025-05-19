package com.shortcut.todo.service;

import com.shortcut.todo.dao.TodoTaskDao;
import com.shortcut.todo.entity.TodoTask;
import com.shortcut.todo.exception.ServiceException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock TodoTaskDao todoTaskDao;
    @InjectMocks TodoService todoService;

    @Test
    public void updateWithNullId() {
        TodoTask todoTask = new TodoTask();
        assertThrows(IllegalArgumentException.class, () -> todoService.updateTodoTask(todoTask));
    }

    @Test
    public void updateWithNotExistId() {
        TodoTask todoTask = new TodoTask();
        todoTask.setId(-1L);
        assertThrows(EntityNotFoundException.class, () -> todoService.updateTodoTask(todoTask));
    }

    @Test
    public void successUpdate() throws ServiceException {
        TodoTask todoTask = new TodoTask();
        todoTask.setId(1L);

        when(todoTaskDao.existsById(todoTask.getId()))
                .thenReturn(true);

        todoService.updateTodoTask(todoTask);
        verify(todoTaskDao, Mockito.times(1)).save(todoTask);
    }

}