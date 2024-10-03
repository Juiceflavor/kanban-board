package com.api.kanban_board.mappers;

import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.TaskModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {

    public TaskMapper() {
    }

    public static TaskModel toModel(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }

        return TaskModel.create(taskDto.getName(), taskDto.getDescription(), taskDto.getParentId(),
                taskDto.getBoardId());
    }

    public static TaskModel toModel(TaskEntity taskEntity) {
        if (taskEntity == null) {
            return null;
        }

        return TaskModel.fromData(taskEntity.getId(), taskEntity.getName(), taskEntity.getStatus(),
                taskEntity.getDescription(), taskEntity.getParentId(), taskEntity.getBoardId());
    }

    public static List<TaskModel> toModelList(List<TaskEntity> taskEntities) {
        if (taskEntities == null) {
            return null;
        }

        return taskEntities.stream().map(TaskMapper::toModel).collect(Collectors.toList());
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
                .parentId(taskModel.getParentId())
                .boardId(taskModel.getBoardId()).build();
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
                .parentId(taskModel.getParentId())
                .boardId(taskModel.getBoardId()).build();
    }

    public static List<TaskDto> toDtoList(List<TaskModel> taskModels) {
        if (taskModels == null) {
            return null;
        }
        return taskModels.stream().map(TaskMapper::toDto).collect(Collectors.toList());
    }
}
