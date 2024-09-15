package com.api.kanban_board.persistences;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.exceptions.CustomException;
import com.api.kanban_board.exceptions.ExceptionDetails;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.persistences.adapters.BoardJpaRepositoryAdapter;
import com.api.kanban_board.repositories.BoardRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.api.kanban_board.mappers.BoardMapper.*;

@Component
public class BoardImplements implements BoardRepository {

    private final BoardJpaRepositoryAdapter boardJpaRepositoryAdapter;

    public BoardImplements(BoardJpaRepositoryAdapter boardJpaRepositoryAdapter) {
        this.boardJpaRepositoryAdapter = boardJpaRepositoryAdapter;
    }

    @Override
    public BoardModel save(BoardModel boardModel) {
        try {
            BoardEntity boardEntity = boardJpaRepositoryAdapter.save(toEntity(boardModel));
            return toModel(boardEntity);
        } catch (DataAccessException e) {
            throw new CustomException("Error saving board in DB",
                    new ExceptionDetails("Database error", "Error"), e);
        }
    }

    @Override
    public BoardModel getBoardById(Long id) {
        try {
            BoardEntity boardEntity = boardJpaRepositoryAdapter.findById(id)
                    .orElseThrow(() -> new CustomException("The board doesn't exist in DB",
                            new ExceptionDetails("Board not found", "Error")));
            return toModel(boardEntity);
        } catch (DataAccessException e) {
            throw new CustomException("Error retrieving board from DB",
                    new ExceptionDetails("Database error", "Error"), e);
        }
    }

    @Override
    public List<BoardModel> getAllBoards() {
        try {
            List<BoardEntity> boardEntities = boardJpaRepositoryAdapter.findAll();
            if (boardEntities.isEmpty()) {
                throw new CustomException("No boards found in DB",
                        new ExceptionDetails("No boards available", "Error"));
            }
            return toModelList(boardEntities);
        } catch (DataAccessException e) {
            throw new CustomException("Error retrieving boards from DB",
                    new ExceptionDetails("Database error", "Error"), e);
        }
    }
}
