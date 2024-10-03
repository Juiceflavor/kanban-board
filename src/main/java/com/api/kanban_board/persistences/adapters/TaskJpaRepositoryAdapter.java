package com.api.kanban_board.persistences.adapters;

import com.api.kanban_board.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskJpaRepositoryAdapter extends JpaRepository<TaskEntity, Integer> {

    public List<TaskEntity> findByBoardIdAndParentIdIsNull(Integer boardId);

    public List<TaskEntity> findByName(String name);

}
