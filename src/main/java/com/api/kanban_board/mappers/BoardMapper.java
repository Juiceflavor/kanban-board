package com.api.kanban_board.mappers;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.dtos.BoardDto;
import com.api.kanban_board.models.BoardModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
                .Status(boardModel.getStatus()).build();

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

        return new BoardDto.Builder()
                .Id(boardModel.getId())
                .Title(boardModel.getTitle())
                .Description(boardModel.getDescription())
                .StatusCode(boardModel.getStatus()).build();
    }

    public static Optional<BoardModel> toModelOptional(Optional<BoardEntity> optionalBoardEntity) {
        return optionalBoardEntity.map(BoardMapper::toModel);
    }

    public static Optional<BoardEntity> toEntityOptional(Optional<BoardModel> optionalBoardModel) {
        return optionalBoardModel.map(BoardMapper::toEntity);
    }
}
