package com.freded.task.server.controller;

import com.freded.dtos.TaskDTO;
import com.freded.dtos.TaskFileDTO;
import com.freded.dtos.TaskFileSortAndPaginationDTO;
import com.freded.file.client.TaskFileClient;
import com.freded.task.client.dto.TaskFileQueryDTO;
import com.freded.task.client.dto.TaskSortAndPaginationDTO;
import com.freded.task.server.entity.TaskEntity;
import com.freded.task.server.exception.ResourceNotFoundException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Objects;

/**
 * Service class for managing task operations. Provides business logic for CRUD operations on tasks with user-specific
 * access control.
 */
@RequestScoped
public class TaskService {
    private static final Logger LOG = Logger.getLogger(TaskService.class);
    @Inject
    TaskRepository taskRepository;

    @Inject
    TaskMapper taskMapper;

    @Inject
    TaskFileClient taskFileClient;

    @Inject
    TaskFilePaginationAndSortingMapper taskFilePaginationAndSortingMapper;


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
     * @param qParams     sorting and pagination parameters
     * @param currentUser the username of the user requesting tasks
     * @return list of tasks as DTOs
     */
    public List<TaskDTO> getAll(final TaskSortAndPaginationDTO qParams, final String currentUser) {
        List<TaskEntity> taskEntities = taskRepository.readAll(currentUser, qParams);

        return taskMapper.toDTOList(taskEntities);
    }

    /**
     * Retrieves a specific task entity by ID for the current user.
     *
     * @param taskId      the unique identifier of the task
     * @param currentUser the username of the user requesting the task
     * @return the task entity
     * @throws ResourceNotFoundException if task is not found
     */
    public TaskEntity get(final String taskId, final String currentUser) {
        TaskEntity taskEntity = taskRepository.read(currentUser, taskId);

        if (taskEntity == null) {
            throw new ResourceNotFoundException("Not found task with ID " + taskId);
        }

        return taskEntity;
    }

    /**
     * Retrieves a specific task by ID with optional file loading.
     *
     * @param taskId      the unique identifier of the task
     * @param currentUser the username of the user requesting the task
     * @param taskParams  query parameters including file loading preference
     * @return the task as DTO with or without files
     * @throws ResourceNotFoundException if task is not found
     */
    public TaskDTO get(final String taskId, final String currentUser, final TaskFileQueryDTO taskParams) {

        TaskEntity taskEntity = taskRepository.read(currentUser, taskId);

        if (taskEntity == null) {
            throw new ResourceNotFoundException("Not found task with ID " + taskId);
        }

        TaskDTO taskDTO = taskMapper.toDTO(taskEntity);

        if (taskParams.isLoadFiles()) {
            return this.loadTaskWithFiles(taskDTO, taskParams);
        } else {
            return taskDTO;
        }
    }

    private TaskDTO loadTaskWithFiles(final TaskDTO taskDTO, final TaskFileQueryDTO taskFileQueryDTO) {

        TaskFileSortAndPaginationDTO taskFileSortAndPaginationDTO =
                taskFilePaginationAndSortingMapper.toTaskFilePaginationDTO(taskFileQueryDTO);
        List<TaskFileDTO> taskFiles = taskFileClient.getFilesForTask(taskDTO.getId(), taskFileSortAndPaginationDTO);


        taskDTO.setTaskFiles(taskFiles);

        return taskDTO;
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