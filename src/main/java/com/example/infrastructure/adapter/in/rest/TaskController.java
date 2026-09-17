package com.example.infrastructure.adapter.in.rest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.application.port.in.CreateTaskUseCase;
import com.example.application.port.in.GetTaskUseCase;
import com.example.application.port.in.ListTaskUseCase;
import com.example.domain.model.Task;
import com.example.infrastructure.adapter.in.rest.dto.CreateTaskRequest;
import com.example.infrastructure.adapter.in.rest.dto.TaskResponse;
import com.example.application.port.in.DeleteTaskUseCase;
import com.example.application.port.in.UpdateTaskUseCase;
import com.example.application.port.in.UploadTaskImageUseCase;
import com.example.infrastructure.adapter.in.rest.dto.UpdateTaskRequest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final GetTaskUseCase getTaskUseCase;
    private final ListTaskUseCase listTaskUseCase;
    private final TaskResMapper taskResMapper;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final UploadTaskImageUseCase uploadTaskImageUseCase;

   @PostMapping
    public ResponseEntity<TaskResponse> create(
            @Valid @RequestBody CreateTaskRequest request) {

        Task task = taskResMapper.toDomain(request);

        Task saved = createTaskUseCase.create(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskResMapper.toTaskResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(
            @PathVariable long id) {

        Task task = getTaskUseCase.getById(id);

        return ResponseEntity.ok(
                taskResMapper.toTaskResponse(task)
        );
    }
    @GetMapping
    public ResponseEntity<List<TaskResponse>> listAll() {

        List<TaskResponse> response = listTaskUseCase.listAll()
                .stream()
                .map(taskResMapper::toTaskResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {

        deleteTaskUseCase.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(
            @PathVariable long id,
            @Valid @RequestBody UpdateTaskRequest request) {

        Task task = taskResMapper.toDomain(request);

        Task updated = updateTaskUseCase.update(id, task);

        return ResponseEntity.ok(
                taskResMapper.toTaskResponse(updated)
        );
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<TaskResponse> uploadImage(
        @PathVariable long id,
        @RequestParam("image") MultipartFile image) throws IOException {

    if (image.isEmpty()) {
        return ResponseEntity.badRequest().build();
    }

    Task updated = uploadTaskImageUseCase.uploadImage(
            id,
            image.getBytes(),
            image.getContentType()
    );

    return ResponseEntity.ok(
            taskResMapper.toTaskResponse(updated)
    );
}


}