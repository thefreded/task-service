package com.freded.task.server.controller;

import com.freded.common.LoggedInUserInfo;
import com.freded.common.annotation.LoggedInUser;
import com.freded.dtos.TaskDTO;
import com.freded.dtos.TaskPaginationAndSortingDTO;
import com.freded.task.server.entity.TaskEntity;
import com.freded.task.server.exception.ResourceNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Service class for managing task operations. Provides business logic for CRUD operations on tasks with user-specific
 * access control.
 */
@Transactional
@ApplicationScoped
public class TaskService {

    @Inject
    TaskRepository taskRepository;

    @Inject
    TaskMapper taskMapper;


    @Inject
    @LoggedInUser
    LoggedInUserInfo loggedInUserInfo;


    /**
     * Creates a new task for the specified user.
     *
     * @param task the task data to create
     * @return the created task as DTO without file associations
     */

    public TaskDTO create(final TaskDTO task) {
        TaskEntity newTask = taskMapper.toEntity(task);

        newTask.setCreatedBy(loggedInUserInfo.getUsername());
        return taskMapper.toDTO(taskRepository.create(newTask));
    }

    /**
     * Retrieves all tasks for the current user with sorting and pagination.
     *
     * @param taskPaginationAndSortingDTO sorting and pagination parameters
     * @return list of tasks as DTOs
     */
    public List<TaskDTO> getAll(final TaskPaginationAndSortingDTO taskPaginationAndSortingDTO) {
        List<TaskEntity> taskEntities = taskRepository.readAll(loggedInUserInfo.getUsername(),
                taskPaginationAndSortingDTO);

        return taskMapper.toDTOList(taskEntities);
    }


    /**
     * Retrieves a specific task by ID
     *
     * @param taskId the unique identifier of the task
     * @return the task as DTO with or without files
     * @throws ResourceNotFoundException if task is not found
     */
    public TaskDTO get(final String taskId) {
        TaskEntity taskEntity = taskRepository.read(loggedInUserInfo.getUsername(), taskId);

        if (taskEntity == null) {
            throw new ResourceNotFoundException("Not found task with ID " + taskId);
        }

        return taskMapper.toDTO(taskEntity);

    }


    /**
     * Deletes a task by ID for the current user.
     *
     * @param taskId the unique identifier of the task to delete
     * @return confirmation message or identifier of the deleted task
     */
    public String delete(final String taskId) {
        TaskEntity task = taskRepository.read(loggedInUserInfo.getUsername(), taskId);


        if (!Objects.equals(taskId, task.getId().toString())) {
            throw new ResourceNotFoundException("Not found task with ID " + taskId);
        }

        return taskRepository.delete(task).toString();

    }

    /**
     * Updates an existing task with new data.
     *
     * @param taskId  the unique identifier of the task to update
     * @param newTask the new task data
     * @return the updated task as DTO without file associations
     * @throws ResourceNotFoundException if task is not found
     */
    public TaskDTO update(final String taskId, final TaskDTO newTask) {

        TaskEntity task = taskRepository.read(loggedInUserInfo.getUsername(), taskId);

        if (task == null) {
            throw new ResourceNotFoundException("Not found task with ID " + taskId);
        }

        // update task with new task inputs
        task.setName(newTask.getName());
        task.setDescription(newTask.getDescription());

        // TODO: downside is I need to flush and refresh to get the latest DB Value. Discuss later.
        return taskMapper.toDTO(taskRepository.update(task));
    }
}