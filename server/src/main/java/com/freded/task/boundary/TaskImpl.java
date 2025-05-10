package com.freded.task.boundary;

import com.freded.dtos.TaskDTO;
import com.freded.entities.TaskEntity;
import com.freded.task.controller.TaskService;
import com.freded.task.controller.UserService;
import com.freded.task.entity.TaskQueryDTO;
import com.freded.task.entity.TaskSortAndPaginationDTO;
import jakarta.inject.Inject;

import java.util.List;


public class TaskImpl implements Task {
    @Inject
    TaskService taskService;

    @Inject
    UserService userService;


    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDTO get(final String taskId, final TaskQueryDTO taskParams) {
        String currentUser = userService.getUsername();
        return taskService.get(taskId, currentUser, taskParams);
    }

    @Override
    public TaskEntity get(String taskId, Boolean returnEntity) {
        String currentUser = userService.getUsername();
        return taskService.get(taskId, currentUser);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDTO create(final TaskDTO task) {
        String currentUser = userService.getUsername();
        return taskService.create(task, currentUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskDTO> getAll(final TaskSortAndPaginationDTO qParams) {
        String currentUser = userService.getUsername();
        return taskService.getAll(qParams, currentUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String delete(final String taskId) {
        String currentUser = userService.getUsername();
        return taskService.delete(taskId, currentUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDTO update(final String taskId, final TaskDTO task) {
        String currentUser = userService.getUsername();
        return taskService.update(taskId, task, currentUser);
    }
}
