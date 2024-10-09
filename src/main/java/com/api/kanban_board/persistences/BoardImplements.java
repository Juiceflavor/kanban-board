package com.api.kanban_board.persistences;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.persistences.adapters.BoardJpaRepositoryAdapter;
import com.api.kanban_board.repositories.BoardRepository;
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
        return toModel(boardJpaRepositoryAdapter.save(toEntity(boardModel)));
    }

    @Override
    public BoardModel getBoardById(Integer id) {
        return toModel(boardJpaRepositoryAdapter.findById(id).get());
    }

    @Override
    public List<BoardModel> getAllBoards() {
        List<BoardEntity> boardEntities = boardJpaRepositoryAdapter.findAll();
        return toModelList(boardEntities);
    }

    @Override
    public List<BoardModel> getBoardsByTitle(String title) {
        List<BoardEntity> boardEntities = boardJpaRepositoryAdapter.findByTitle(title);
        return toModelList(boardEntities);
    }

    @Override
    public BoardModel transitionBoard(Integer id) {
        BoardModel boardModel = getBoardById(id);
        return toModel(boardJpaRepositoryAdapter.save(toEntity(boardModel.transition())));
    }

    @Override
    public BoardModel inactive(Integer id) {
        BoardModel boardModel = getBoardById(id);
        return toModel(boardJpaRepositoryAdapter.save(toEntity(boardModel.inactive())));
    }

    @Override
    public BoardModel active(Integer id) {
        BoardModel boardModel = getBoardById(id);
        return toModel(boardJpaRepositoryAdapter.save(toEntity(boardModel.active())));
    }
}
