package com.example.application.service;

import java.util.List;

import com.example.application.port.in.CreateTaskUseCase;
import com.example.application.port.in.DeleteTaskUseCase;
import com.example.application.port.in.GetTaskUseCase;
import com.example.application.port.in.ListTaskUseCase;
import com.example.application.port.in.UpdateTaskUseCase;
import com.example.application.port.in.UploadTaskImageUseCase;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.exception.TaskNotFoundException;
import com.example.domain.model.Task;

public class TaskService implements 
        CreateTaskUseCase, 
        GetTaskUseCase, 
        ListTaskUseCase, 
        DeleteTaskUseCase,
        UpdateTaskUseCase,
        UploadTaskImageUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public TaskService(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Task create(Task task) {
        return taskRepositoryPort.save(task);
    }

    @Override
    public Task getById(long id) {

        return taskRepositoryPort.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public List<Task> listAll() {
        return taskRepositoryPort.findAll();
    }

    @Override
    public void deleteById(long id) {

        taskRepositoryPort.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskRepositoryPort.deleteById(id);
    }

    @Override
    public Task update(long id, Task task) {

        Task existingTask = taskRepositoryPort.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        Task updatedTask = new Task(
                existingTask.id(),
                task.title(),
                task.description(),
                existingTask.status(),
                existingTask.createdAt(),
                existingTask.completedAt(),
                existingTask.image(),
                existingTask.imageContentType()
        );

        return taskRepositoryPort.save(updatedTask);
    }

    @Override
    public Task uploadImage(long id, byte[] image, String contentType) {

        Task existingTask = taskRepositoryPort.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        Task updatedTask = new Task(
                existingTask.id(),
                existingTask.title(),
                existingTask.description(),
                existingTask.status(),
                existingTask.createdAt(),
                existingTask.completedAt(),
                image,
                contentType
        );

        return taskRepositoryPort.save(updatedTask);
    }
}