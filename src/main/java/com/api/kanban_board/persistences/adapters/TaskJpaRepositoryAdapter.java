package com.api.kanban_board.persistences.adapters;

import com.api.kanban_board.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskJpaRepositoryAdapter extends JpaRepository<TaskEntity, Long> {

    @Query(value = "SELECT * FROM TASKS t WHERE t.BOARD_ID  = ?1 AND t.PARENT_ID IS NULL", nativeQuery = true)
    public List<TaskEntity> getTasksByBoardId(Long board_id);
}
