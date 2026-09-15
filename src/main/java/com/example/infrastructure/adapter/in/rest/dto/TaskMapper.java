package com.example.infrastructure.adapter.in.rest.dto;

import org.mapstruct.Mapper;

import com.example.domain.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponse toTaskResponse(Task task);

    Task toTask(CreateTaskRequest request);
}