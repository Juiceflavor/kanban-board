package com.api.kanban_board.persistences.adapters;

import com.api.kanban_board.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskJpaRepositoryAdapter extends JpaRepository<TaskEntity, Integer> {

    @Query(value = "SELECT * FROM TASKS t WHERE t.BOARDID  = ?1 AND t.PARENTID IS NULL", nativeQuery = true)
    public List<TaskEntity> getTasksByBoardId(Integer boardId);

    @Query(value = "SELECT * FROM TASKS t WHERE t.NAME  = ?1", nativeQuery = true)
    public List<TaskEntity> getTasksByName(String name);
}
