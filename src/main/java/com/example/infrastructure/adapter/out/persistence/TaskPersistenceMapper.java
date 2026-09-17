package com.example.infrastructure.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.example.domain.model.Task;


@Mapper (componentModel = "spring")
public interface TaskPersistenceMapper {

    Task toDomain(TaskJpaEntity entity);
    TaskJpaEntity toJpaEntity(Task task);
    
}
