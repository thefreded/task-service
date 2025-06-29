package com.freded.task.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Represents a Task in the dto manager. It includes fields for task identification, name, description, creation date,
 * last updated date, and the user who created the task.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {

    /**
     * Unique identifier for the task, generated randomly.
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * The name of the task. The field must be unique and not be null.
     */
    @NotNull(message = "Task must have a name")
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * The description of the task
     */
    private String description;

    /**
     * The local date time that the task was created. It's set automatically by default with the current date and time
     * the task was created.
     */
    private ZonedDateTime createdAt;

    /**
     * The local date time when the task was updated (or last updated).
     */
    private ZonedDateTime updatedAt;

    /**
     * The username of the logged-in user who created the task.
     */
    private String createdBy;

    @PrePersist
    protected void onCreate() {
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = ZonedDateTime.now(ZoneOffset.UTC);
    }
}
