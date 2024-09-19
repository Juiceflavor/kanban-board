package com.api.kanban_board.persistences.adapters;

import com.api.kanban_board.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskJpaRepositoryAdapter extends JpaRepository<TaskEntity, Integer> {

    List<TaskEntity> findByBoardIdAndParentIdIsNull(Integer boardId);

    List<TaskEntity> findByParentIdAndBoardIdIsNull(Integer parentId);

    List<TaskEntity> findByName(String name);

}
