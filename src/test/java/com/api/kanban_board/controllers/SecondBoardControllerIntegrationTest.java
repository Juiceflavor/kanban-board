package com.api.kanban_board.controllers;

import com.api.kanban_board.MockUtils;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.models.TaskModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SecondBoardControllerIntegrationTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private BoardModel boardModel;

    @Autowired
    SecondBoardControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


    @BeforeAll
    void setUp() {
        boardModel = MockUtils.makeBoardModelMock();
    }

    @Test
    @Order(1)
    void shouldCreateBoard() throws Exception {
        // Arrange
        BoardModel newBoard = MockUtils.makeBoardModelMock();

        // Act & Assert
        mockMvc.perform(post("/api/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newBoard)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Primer tablero"))
                .andExpect(jsonPath("$.description").value("Primer tablero"))
                .andExpect(jsonPath("$.statusCode").value("001"));
    }

    @Test
    @Order(2)
    void shouldGetBoardById() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/boards/{id}", boardModel.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(boardModel.getTitle()))
                .andExpect(jsonPath("$.description").value(boardModel.getDescription()))
                .andExpect(jsonPath("$.statusCode").value(boardModel.getStatus().getCode()));
    }

    @Test
    @Order(3)
    void shouldGetAllBoards() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/boards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value(boardModel.getTitle()))
                .andExpect(jsonPath("$[0].description").value(boardModel.getDescription()))
                .andExpect(jsonPath("$[0].statusCode").value(boardModel.getStatus().getCode()));
    }

    @Test
    @Order(4)
    void shouldGetAllTaskByBoardId () throws Exception {
        // Arrange
        TaskModel newTask = TaskModel.create("Tarea 1", "Tarea 1",
                boardModel.getId(), null);
        ResultActions resultActions = mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTask)));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(resultActions.andReturn().getResponse().getContentAsString());

        // Act & Assert
        mockMvc.perform(get("/api/boards/{id}/tasks", jsonNode.get("boardId").asInt()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Order(5)
    void shouldTransitionBoard() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/boards/{id}/transition", boardModel.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("002"));
    }

    @Test
    @Order(6)
    void shouldInactiveBoard () throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/boards/{id}/inactive", boardModel.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("000"));
    }

    @Test
    @Order(7)
    void shouldActiveBoard() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/boards/{id}/active", boardModel.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value("001"));
    }
}