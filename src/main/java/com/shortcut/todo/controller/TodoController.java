package com.shortcut.todo.controller;

import com.shortcut.todo.entity.TodoTask;
import com.shortcut.todo.entity.filter.TodoTaskFilter;
import com.shortcut.todo.exception.ServiceException;
import com.shortcut.todo.service.TodoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {
    @Resource private TodoService todoService;

    @Tag(name = "post", description = "POST-методы сервиса")
    @PostMapping("/create")
    public TodoTask createTodoTask(@ModelAttribute TodoTask todoTask) {
        return todoService.createTodoTask(todoTask);
    }

    @Tag(name = "get", description = "GET-методы сервиса")
    @GetMapping("/get/{id}")
    public TodoTask getTodoTaskById(@PathVariable Long id) {
        return todoService.getTodoTaskById(id);
    }


    @Tag(name = "post", description = "POST-методы сервиса")
    @PostMapping("/list")
    public List<TodoTask> getTodoTasks(@ModelAttribute TodoTaskFilter todoTaskFilter) {
        return todoService.getTodoTasks(todoTaskFilter);
    }

    @ApiResponses ({
        @ApiResponse(responseCode = "200", description = "Задача успешно обновлена")
    })
    @Tag(name = "post", description = "POST-методы сервиса")
    @PostMapping("/update")
    public ResponseEntity<?> updateTodoTask(@ModelAttribute TodoTask todoTask) throws ServiceException {
            return new ResponseEntity<>(todoService.updateTodoTask(todoTask), OK);
    }

    @Tag(name = "post", description = "POST-методы сервиса")
    @PostMapping("/delete/{id}")
    public void deleteTodoTask(@PathVariable Long id) throws ServiceException {
        todoService.deleteTodoTaskById(id);
    }
}
