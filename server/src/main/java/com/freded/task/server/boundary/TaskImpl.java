package com.freded.task.server.boundary;

import com.freded.dtos.TaskDTO;
import com.freded.dtos.TaskPaginationAndSortingDTO;
import com.freded.task.client.boundary.Task;
import com.freded.task.server.controller.TaskService;
import com.freded.task.server.controller.UserService;
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
    public TaskDTO get(final String taskId) {
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
    public List<TaskDTO> getAll(final TaskPaginationAndSortingDTO qParams) {
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
