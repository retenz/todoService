package com.shortcut.todo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired private MockMvc mockMvc;

    @Test
    public void getTodoTasks() throws Exception {
        String todoTaskFilter = """
        {
            "sortByCreateDate" : "true",
            "taskStatus" : {
                "id" : "1",
                "name" : "Новая"
            }
        }
        """;

        mockMvc.perform(post("/todo/list")
                        .contentType(APPLICATION_JSON)
                        .content(todoTaskFilter))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("new task"))
                .andExpect(jsonPath("$[0].description").value("testing update task by postman"))
                .andExpect(jsonPath("$[0].clsTaskStatus.id").value("1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}