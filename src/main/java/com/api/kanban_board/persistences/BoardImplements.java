package com.api.kanban_board.persistences;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.persistences.adapters.BoardJpaRepositoryAdapter;
import com.api.kanban_board.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.api.kanban_board.mappers.BoardMapper.*;

@Component
public class BoardImplements implements BoardRepository {

    @Autowired
    private BoardJpaRepositoryAdapter boardJpaRepositoryAdapter;

    @Override
    public BoardModel save(BoardModel boardModel) {
        return toModel(boardJpaRepositoryAdapter.save(toEntity(boardModel)));
    }

    @Override
    public BoardModel getBoardById(Long id) {
        return toModel(boardJpaRepositoryAdapter.findById(id).get());
    }

    @Override
    public List<BoardModel> getAllBoards() {
        List<BoardEntity> boardEntities = boardJpaRepositoryAdapter.findAll();
        return toModel(boardEntities);
    }
}
