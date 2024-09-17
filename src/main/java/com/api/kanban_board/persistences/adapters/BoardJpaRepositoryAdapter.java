package com.api.kanban_board.persistences.adapters;

import com.api.kanban_board.entities.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardJpaRepositoryAdapter extends JpaRepository<BoardEntity, Integer> {

    public List<BoardEntity> findByTitle(String title);
}
