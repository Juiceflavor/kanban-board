package com.api.kanban_board.persistences.adapters;
import com.api.kanban_board.entities.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardJpaRepositoryAdapter extends JpaRepository<BoardEntity, Long> {
}