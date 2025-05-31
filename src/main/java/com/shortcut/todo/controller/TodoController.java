package com.shortcut.todo.controller;

import com.shortcut.todo.entity.TodoTask;
import com.shortcut.todo.entity.filter.TodoTaskFilter;
import com.shortcut.todo.exception.ServiceException;
import com.shortcut.todo.service.TodoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {
    @Resource private TodoService todoService;

    @Tag(name = "post", description = "POST-методы сервиса")
    @PostMapping("/create")
    public TodoTask createTodoTask(@RequestBody TodoTask todoTask) {
        return todoService.createTodoTask(todoTask);
    }

    @Tag(name = "get", description = "GET-методы сервиса")
    @GetMapping("/get/{id}")
    public Optional<TodoTask> getTodoTaskById(@PathVariable Long id) {
        return todoService.getTodoTaskById(id);
    }


    @Tag(name = "post", description = "POST-методы сервиса")
    @PostMapping("/list")
    public List<TodoTask> getTodoTasks(@RequestBody TodoTaskFilter todoTaskFilter) {
        return todoService.getTodoTasks(todoTaskFilter).toList();
    }

    @ApiResponses ({
        @ApiResponse(responseCode = "200", description = "Задача успешно обновлена")
    })
    @Tag(name = "post", description = "POST-методы сервиса")
    @PostMapping("/update")
    public ResponseEntity<?> updateTodoTask(@Valid @RequestBody TodoTask todoTask, BindingResult result) throws ServiceException {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return ResponseEntity.ok(todoService.updateTodoTask(todoTask));
    }

    @Tag(name = "post", description = "POST-методы сервиса")
    @DeleteMapping("/delete/{id}")
    public void deleteTodoTask(@PathVariable Long id) throws ServiceException {
        todoService.deleteTodoTaskById(id);
    }
}
