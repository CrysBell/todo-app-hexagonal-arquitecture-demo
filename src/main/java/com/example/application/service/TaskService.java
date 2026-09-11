/* Implementa el caso de uso */
package com.example.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.port.in.CreateTaskUseCase;
import com.example.application.port.in.GetTaskUseCase;
import com.example.application.port.in.ListTaskUseCase;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.exception.TaskNotFoundException;
import com.example.domain.model.Task;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service 
/* ¿Es correcta una anotacion de Spring aqui?
 * 
 * Los mas puristas dirian que NO, pero tiene un coste implementar esto
 * correctamente.
 * 
 * Con esta anotacion estamos introduciendo una dependencia del framework
 * en la capa de aplicacion, y el problema es que si mañana migramos a quarkus
 * o cualquier otro framework o si queremos testear el caso de uso en aislamiento total, esta clase
 * ya no seria agnostica del framework, es decir, estaria acoplada el Spring Framework
 * 
 * TODO: ¿Que deberia hacerse para que este acoplamiento no existiera?
 */
public class TaskService implements CreateTaskUseCase, GetTaskUseCase, ListTaskUseCase{

    private final TaskRepositoryPort taskRepositoryPort;

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

    
}