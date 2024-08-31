package com.api.kanban_board.mappers;

import com.api.kanban_board.dtos.TaskDto;
import com.api.kanban_board.entities.TaskEntity;
import com.api.kanban_board.models.TaskModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public TaskMapper() {
    }

    public static TaskModel toModelList(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }

        return TaskModel.create(taskDto.getName(), taskDto.getDescription(), taskDto.getParent_id(),
                taskDto.getBoard_id());
    }

    public static TaskModel toModelList(TaskEntity taskEntity) {
        if (taskEntity == null) {
            return null;
        }

        return TaskModel.fromData(taskEntity.getId(), taskEntity.getName(), taskEntity.getStatus(),
                taskEntity.getDescription(), taskEntity.getParent_id(), taskEntity.getBoard_id());
    }

    public static List<TaskModel> toModelList(List<TaskEntity> taskEntities) {
        if (taskEntities == null) {
            return null;
        }

        return taskEntities.stream().map(TaskMapper::toModelList).collect(Collectors.toList());
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

    public static TaskDto toDtoList(TaskModel taskModel) {
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

    public static List<TaskDto> toDtoList(List<TaskModel> taskModels) {
        if (taskModels == null) {
            return null;
        }
        return taskModels.stream().map(TaskMapper::toDtoList).collect(Collectors.toList());
    }
}
