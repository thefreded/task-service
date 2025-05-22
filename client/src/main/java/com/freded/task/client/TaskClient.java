package com.freded.task.client;

import com.freded.dtos.TaskDTO;
import com.freded.entities.TaskEntity;
import com.freded.task.client.entity.TaskQueryDTO;
import com.freded.task.client.entity.TaskSortAndPaginationDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

/**
 * Provides a simplified interface for task management by delegating
 * to the underlying REST client implementation.
 */
@ApplicationScoped
public class TaskClient {

    @Inject
    @RestClient
    TaskRestClient taskRestClient;

    /**
     * Retrieves all tasks with optional sorting and pagination.
     *
     * @param qParams sorting and pagination parameters
     * @return list of tasks as DTOs
     */
    public List<TaskDTO> getAll(final TaskSortAndPaginationDTO qParams) {
        return taskRestClient.getAll(qParams);
    }

    /**
     * Creates a new task.
     *
     * @param task the task data to create
     * @return the created task as DTO
     */
    public TaskDTO create(final TaskDTO task) {
        return taskRestClient.create(task);
    }

    /**
     * Retrieves a specific task by ID with query parameters.
     *
     * @param taskId     the unique identifier of the task
     * @param taskParams query parameters including file loading preferences
     * @return the task as DTO, or null if not found
     */
    public TaskDTO get(final String taskId, final TaskQueryDTO taskParams) {
        return taskRestClient.get(taskId, taskParams);
    }

    /**
     * Retrieves a specific task by ID with option to return as entity.
     *
     * @param taskId       the unique identifier of the task
     * @param returnEntity flag to determine return type (entity vs DTO)
     * @return the task as entity
     */
    public TaskEntity get(final String taskId, final Boolean returnEntity) {
        return taskRestClient.get(taskId, returnEntity);
    }

    /**
     * Deletes a task by ID.
     *
     * @param taskId the unique identifier of the task to delete
     * @return confirmation message or identifier of the deleted task
     */
    public String delete(final String taskId) {
        return taskRestClient.delete(taskId);
    }

    /**
     * Updates an existing task with new data.
     *
     * @param taskId the unique identifier of the task to update
     * @param task   the updated task data
     * @return the updated task as DTO
     */
    public TaskDTO update(final String taskId, final TaskDTO task) {
        return taskRestClient.update(taskId, task);
    }
}