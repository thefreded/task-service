package com.freded.task.client;

import com.freded.dtos.TaskDTO;
import com.freded.dtos.TaskPaginationAndSortingDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

/**
 * Provides a simplified interface for task management by delegating to the underlying REST client implementation.
 */
@ApplicationScoped
public class TaskClient {

    @Inject
    @RestClient
    TaskRestClient taskRestClient;

    /**
     * Retrieves all tasks with optional sorting and pagination.
     *
     * @param taskPaginationAndSortingDTO sorting and pagination parameters
     * @return list of tasks as DTOs
     */
    public List<TaskDTO> getAll(final TaskPaginationAndSortingDTO taskPaginationAndSortingDTO) {
        return taskRestClient.getAll(taskPaginationAndSortingDTO);
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
     * Retrieves a specific task by ID.
     *
     * @param taskId the unique identifier of the task
     * @return the task as DTO, or null if not found
     */
    public TaskDTO get(final String taskId) {
        return taskRestClient.get(taskId);
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