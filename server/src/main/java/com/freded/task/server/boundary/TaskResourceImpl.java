package com.freded.task.server.boundary;

import com.freded.dtos.TaskDTO;
import com.freded.dtos.TaskPaginationAndSortingDTO;
import com.freded.task.client.boundary.TaskResource;
import com.freded.task.server.controller.TaskService;
import jakarta.inject.Inject;

import java.util.List;


public class TaskResourceImpl implements TaskResource {

    @Inject
    TaskService taskService;


    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDTO get(final String taskId) {
        return taskService.get(taskId);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDTO create(final TaskDTO task) {
        return taskService.create(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskDTO> getAll(final TaskPaginationAndSortingDTO qParams) {
        return taskService.getAll(qParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String delete(final String taskId) {
        return taskService.delete(taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskDTO update(final String taskId, final TaskDTO task) {
        return taskService.update(taskId, task);
    }
}
