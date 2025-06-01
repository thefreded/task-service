package com.freded.task.server.controller;

import com.freded.dtos.TaskDTO;
import com.freded.dtos.TaskPaginationAndSortingDTO;
import com.freded.task.server.entity.TaskEntity;
import com.freded.task.server.exception.ResourceNotFoundException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Service class for managing task operations. Provides business logic for CRUD operations on tasks with user-specific
 * access control.
 */
@RequestScoped
public class TaskService {

    @Inject
    TaskRepository taskRepository;

    @Inject
    TaskMapper taskMapper;

    
    /**
     * Creates a new task for the specified user.
     *
     * @param task        the task data to create
     * @param currentUser the username of the user creating the task
     * @return the created task as DTO without file associations
     */
    @Transactional
    public TaskDTO create(final TaskDTO task, final String currentUser) {
        TaskEntity newTask = taskMapper.toEntity(task);

        newTask.setCreatedBy(currentUser);
        return taskMapper.toDTO(taskRepository.create(newTask));
    }

    /**
     * Retrieves all tasks for the current user with sorting and pagination.
     *
     * @param taskPaginationAndSortingDTO sorting and pagination parameters
     * @param currentUser                 the username of the user requesting tasks
     * @return list of tasks as DTOs
     */
    public List<TaskDTO> getAll(final TaskPaginationAndSortingDTO taskPaginationAndSortingDTO,
            final String currentUser) {
        List<TaskEntity> taskEntities = taskRepository.readAll(currentUser, taskPaginationAndSortingDTO);

        return taskMapper.toDTOList(taskEntities);
    }


    /**
     * Retrieves a specific task by ID
     *
     * @param taskId      the unique identifier of the task
     * @param currentUser the username of the user requesting the task
     * @return the task as DTO with or without files
     * @throws ResourceNotFoundException if task is not found
     */
    public TaskDTO get(final String taskId, final String currentUser) {

        TaskEntity taskEntity = taskRepository.read(currentUser, taskId);

        if (taskEntity == null) {
            throw new ResourceNotFoundException("Not found task with ID " + taskId);
        }

        return taskMapper.toDTO(taskEntity);

    }


    /**
     * Deletes a task by ID for the current user.
     *
     * @param taskId      the unique identifier of the task to delete
     * @param currentUser the username of the user deleting the task
     * @return confirmation message or identifier of the deleted task
     */
    @Transactional
    public String delete(final String taskId, final String currentUser) {

        TaskEntity task = taskRepository.read(currentUser, taskId);
        String deletedTaskId = taskRepository.delete(task);

        if (!Objects.equals(taskId, deletedTaskId)) {
            throw new ResourceNotFoundException("Not found task with ID " + taskId);
        }

        return deletedTaskId;
    }

    /**
     * Updates an existing task with new data.
     *
     * @param taskId      the unique identifier of the task to update
     * @param newTask     the new task data
     * @param currentUser the username of the user updating the task
     * @return the updated task as DTO without file associations
     * @throws ResourceNotFoundException if task is not found
     */
    @Transactional
    public TaskDTO update(final String taskId, final TaskDTO newTask, final String currentUser) {

        TaskEntity task = taskRepository.read(currentUser, taskId);

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