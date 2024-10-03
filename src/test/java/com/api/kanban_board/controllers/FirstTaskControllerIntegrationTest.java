package com.api.kanban_board.controllers;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.TaskModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FirstTaskControllerIntegrationTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    FirstTaskControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @Order(1)
    void shouldCreateTask() throws Exception {
        // Arrange
        TaskModel newTask = MockUtils.makeTaskModelMock();

        // Act & Assert
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTask)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Primera tarea"))
                .andExpect(jsonPath("$.description").value("Primera tarea"))
                .andExpect(jsonPath("$.statusCode").value("001"));
    }

    @Test
    @Order(2)
    void shouldGetTaskById() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Primera tarea"))
                .andExpect(jsonPath("$.description").value("Primera tarea"))
                .andExpect(jsonPath("$.statusCode").value("001"));
    }

    @Test
    @Order(3)
    void shouldGetAllTasks() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Primera tarea"))
                .andExpect(jsonPath("$[0].description").value("Primera tarea"))
                .andExpect(jsonPath("$[0].statusCode").value("001"));
    }

    @Test
    @Order(4)
    void shouldGetTaskByParentId() throws Exception {
        // Arrange
        TaskModel newChildTask = TaskModel.create("Primera subtarea", "Primera subtarea",
                1, null);
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newChildTask)));

        // Act & Assert
        mockMvc.perform(get("/api/tasks/1/children_tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Primera subtarea"))
                .andExpect(jsonPath("$[0].description").value("Primera subtarea"))
                .andExpect(jsonPath("$[0].statusCode").value("001"))
                .andExpect(jsonPath("$[0].parentId").value(1));
    }

    @Test
    @Order(5)
    void shouldTransitionTask() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/tasks/1/transition"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Primera tarea"))
                .andExpect(jsonPath("$.description").value("Primera tarea"))
                .andExpect(jsonPath("$.statusCode").value("002"));
    }

    @Test
    @Order(6)
    void shouldInactiveTask() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/tasks/1/inactive"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Primera tarea"))
                .andExpect(jsonPath("$.description").value("Primera tarea"))
                .andExpect(jsonPath("$.statusCode").value("000"));
    }

    @Test
    @Order(7)
    void shouldActiveTask() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/tasks/1/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Primera tarea"))
                .andExpect(jsonPath("$.description").value("Primera tarea"))
                .andExpect(jsonPath("$.statusCode").value("001"));
    }
}
