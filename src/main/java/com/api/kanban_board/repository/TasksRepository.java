package com.api.kanban_board.repository;

import com.api.kanban_board.model.Tasks;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
    @Query(value = "SELECT * FROM TASKS t WHERE t.BOARD_ID  = ?1 AND t.PARENT_ID IS NULL", nativeQuery = true)
    public List<Tasks> getTasksByBoardId(Long board_id);
    
    
    @Query(value = "SELECT * FROM TASKS t WHERE t.PARENT_ID  = ?1", nativeQuery = true)
    public List<Tasks> getTasksByParentId(Long parent_id);
}
