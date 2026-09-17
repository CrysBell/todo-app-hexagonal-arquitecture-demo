package com.example.domain.model;

import java.time.LocalDateTime;

/* CONVERTIDO A RECORD */
public record Task(
        long id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDateTime completedAt,
        byte[] image,
        String imageContentType
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
                LocalDateTime.now(),
                this.image,
                this.imageContentType
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
                null,
                this.image,
                this.imageContentType
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
                this.completedAt,
                this.image,
                this.imageContentType
        );
    }
}