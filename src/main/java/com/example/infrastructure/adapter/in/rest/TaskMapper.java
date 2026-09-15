package com.example.infrastructure.adapter.in.rest;

import org.mapstruct.Mapper;

import com.example.domain.model.Task;
import com.example.infrastructure.adapter.in.rest.dto.CreateTaskRequest;
import com.example.infrastructure.adapter.in.rest.dto.TaskResponse;
import com.example.infrastructure.adapter.in.rest.dto.UpdateTaskRequest;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponse toTaskResponse(Task task);

    Task toTask (CreateTaskRequest request);

    Task toTask (UpdateTaskRequest request);
}