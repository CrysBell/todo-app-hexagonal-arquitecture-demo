package com.example.infrastructure.adapter.out.persistence;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.domain.model.Task;


@Mapper (componentModel = "spring")
public interface TaskPersistenceMapper {

    Task toDomain(TaskJpaEntity entity);
    TaskJpaEntity toJpaEntity(Task task);
    
}







/* 
*@Component
public class TaskPersistenceMapper {
    /* Implementado con MapStruct */

  /* 
  private TaskMapper taskMapper;
    
    Task task = taskMapper.toDomain(TaskJpaEntity entity);
  
  */  

    /*
     * 
     * public Task toDomain(TaskJpaEntity entity) {
     * if (entity == null)
     * return null;
     * 
     * return new Task(
     * entity.getId(),
     * entity.getTitle(),
     * entity.getDescription(),
     * entity.getStatus(),
     * entity.getCreatedAt(),
     * entity.getCompletedAt()
     * );
     * }
     * 
     * public TaskJpaEntity toJpaEntity(Task task) {
     * 
     * if (task == null)
     * return null;
     * 
     * return TaskJpaEntity.builder()
     * .id(task.id())
     * .title(task.title())
     * .description(task.description())
     * .status(task.status())
     * .createdAt(task.createdAt())
     * .completedAt(task.completedAt())
     * .build();
     * }
     
}*/