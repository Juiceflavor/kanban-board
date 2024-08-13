package com.api.kanban_board.persistences;

import com.api.kanban_board.entities.BoardEntity;
import com.api.kanban_board.models.BoardModel;
import com.api.kanban_board.persistences.adapters.BoardJpaRepositoryAdapter;
import com.api.kanban_board.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public BoardEntity getBoardById(Long id) {
        return boardJpaRepositoryAdapter.findById(id).get();
    }
}
