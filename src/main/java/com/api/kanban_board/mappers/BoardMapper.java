package com.api.kanban_board.mappers;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.dtos.BoardDto;
import com.api.kanban_board.models.BoardModel;
import org.springframework.stereotype.Component;

@Component
public final class BoardMapper {

    private BoardMapper(){
    }

    public static BoardModel toModel(BoardDto boardDto) {
        if (boardDto == null) {
            return null;
        }

        BoardModel boardModel = BoardModel.fromData(boardDto.getId(), boardDto.getTitle(), boardDto.getDescription(), boardDto.getStatusCode());

        return boardModel;
    }

    public static BoardEntity toEntity(BoardModel boardModel){
        if (boardModel == null) {
            return null;
        }

        BoardEntity boardEntity = new BoardEntity.Builder()
                .Id(boardModel.getId())
                .Title(boardModel.getTitle())
                .Description(boardModel.getDescription())
                .Status(boardModel.getStatus().getCode()).build();

        return boardEntity;
    }

    public static BoardModel toModel(BoardEntity boardEntity){
        if (boardEntity == null) {
            return null;
        }

        BoardModel boardModel = BoardModel.fromData(boardEntity.getId(), boardEntity.getTitle(), boardEntity.getDescription(), boardEntity.getStatus());

        return boardModel;
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
}
