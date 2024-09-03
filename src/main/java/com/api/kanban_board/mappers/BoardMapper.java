package com.api.kanban_board.mappers;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.dtos.BoardDto;
import com.api.kanban_board.models.BoardModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class BoardMapper {

    private BoardMapper(){
    }

    public static BoardModel toModel(BoardDto boardDto) {
        if (boardDto == null) {
            return null;
        }

        BoardModel boardModel = BoardModel.create(boardDto.getTitle(), boardDto.getDescription());

        return boardModel;
    }

    public static BoardModel toModel(BoardEntity boardEntity){
        if (boardEntity == null) {
            return null;
        }

        BoardModel boardModel = BoardModel.fromData(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getDescription(), boardEntity.getStatus());

        return boardModel;
    }

    public static List<BoardModel> toModelList(List<BoardEntity> boardEntitiesList) {
        if (boardEntitiesList == null) {
            return null;
        }
        return boardEntitiesList.stream().map(entity -> toModel(entity)).collect(Collectors.toList());
    }

    public static BoardEntity toEntity(BoardModel boardModel){
        if (boardModel == null) {
            return null;
        }

        BoardEntity boardEntity = BoardEntity.builder()
                .id(boardModel.getId())
                .title(boardModel.getTitle())
                .description(boardModel.getDescription())
                .status(boardModel.getStatus().getCode()).build();

        return boardEntity;
    }

    public static BoardDto toDto(BoardModel boardModel){
        if (boardModel == null) {
            return null;
        }

        return BoardDto.builder()
                .id(boardModel.getId())
                .title(boardModel.getTitle())
                .description(boardModel.getDescription())
                .statusCode(boardModel.getStatus().getCode()).build();
    }

    public static List<BoardDto> toDtoList(List<BoardModel> boardModelList){
        if (boardModelList == null) {
            return null;
        }
        return boardModelList.stream().map(entity -> toDto(entity)).collect(Collectors.toList());
    }
}