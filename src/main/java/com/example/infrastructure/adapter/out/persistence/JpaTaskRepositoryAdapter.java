package com.example.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Repository;

import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.model.Task;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {

    private final SpringDataTaskRepository springDataTaskRepository;
    private final TaskPersistenceMapper mapper;


    @Override
    public Task save(Task task) {

        task.initDefaults();
        TaskJpaEntity entity = mapper.toJpaEntity(task);
        TaskJpaEntity saved = springDataTaskRepository.save(entity);
        
        return mapper.toDomain(saved);
    }

}