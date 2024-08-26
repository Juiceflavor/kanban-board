package com.api.kanban_board.persistences.adapters;

import com.api.kanban_board.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskJpaRepositoryAdapter extends JpaRepository<TaskEntity, Long> {
}
