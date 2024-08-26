package com.api.kanban_board.mappers;

import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.TaskModel;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskMapper() {
    }

    public static TaskModel toModel(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }

        return TaskModel.create(taskDto.getName(), taskDto.getDescription(), taskDto.getParent_id(),
                taskDto.getBoard_id());
    }

    public static TaskEntity toEntity(TaskModel taskModel) {
        if (taskModel == null) {
            return null;
        }

        return TaskEntity.builder()
                .id(taskModel.getId())
                .name(taskModel.getName())
                .description(taskModel.getDescription())
                .status(taskModel.getStatus().getCode())
                .parent_id(taskModel.getParent_id())
                .board_id(taskModel.getBoard_id()).build();
    }

    public static TaskModel toModel(TaskEntity taskEntity) {
        if (taskEntity == null) {
            return null;
        }

        return TaskModel.fromData(taskEntity.getId(), taskEntity.getName(), taskEntity.getStatus(),
                taskEntity.getDescription(), taskEntity.getParent_id(), taskEntity.getBoard_id());
    }

    public static TaskDto toDto(TaskModel taskModel) {
        if (taskModel == null) {
            return null;
        }

        return TaskDto.builder()
                .id(taskModel.getId())
                .name(taskModel.getName())
                .description(taskModel.getDescription())
                .statusCode(taskModel.getStatus().getCode())
                .parent_id(taskModel.getParent_id())
                .board_id(taskModel.getBoard_id()).build();
    }
}
