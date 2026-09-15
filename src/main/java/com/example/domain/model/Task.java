package com.example.domain.model;

import java.time.LocalDateTime;

/* CONVERTIDO A RECORD */
public record Task(
        long id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDateTime completedAt
) {

    /*
     * Los métodos siguientes aportan comportamiento,
     * es decir, las reglas del negocio para la gestión de las tareas.
     */

    public Task complete() {

        if (this.status == TaskStatus.COMPLETED) {
            throw new IllegalStateException(
                    "La tarea ya está completada"
            );
        }

        return new Task(
                this.id,
                this.title,
                this.description,
                TaskStatus.COMPLETED,
                this.createdAt,
                LocalDateTime.now()
        );
    }

    public Task reopen() {

        if (this.status == TaskStatus.PENDING) {
            throw new IllegalStateException(
                    "La tarea ya está pendiente"
            );
        }

        return new Task(
                this.id,
                this.title,
                this.description,
                TaskStatus.PENDING,
                this.createdAt,
                null
        );
    }

    public Task initDefaults() {

        TaskStatus newStatus =
                this.status == null
                        ? TaskStatus.PENDING
                        : this.status;

        LocalDateTime newCreatedAt =
                this.createdAt == null
                        ? LocalDateTime.now()
                        : this.createdAt;

        return new Task(
                this.id,
                this.title,
                this.description,
                newStatus,
                newCreatedAt,
                this.completedAt
        );
    }
}