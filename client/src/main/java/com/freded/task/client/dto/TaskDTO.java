package com.freded.task.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * DTO for task
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    /**
     * Unique identifier for the task.
     */
    private UUID id;

    /**
     * The name of the task.
     */
    private String name;

    /**
     * The description of the task
     */
    private String description;

    /**
     * The zoned date time that the task was created.
     */
    private ZonedDateTime createdAt;

    /**
     * The zoned date time when the task was updated (or last updated).
     */
    private ZonedDateTime updatedAt;

    /**
     * Creator of task.
     */
    private String createdBy;


}

