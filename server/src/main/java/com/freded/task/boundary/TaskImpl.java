package com.freded.task.boundary;



import com.freded.dtos.TaskDTO;
import com.freded.entities.TaskEntity;
import com.freded.task.controller.TaskService;
import com.freded.task.controller.UserService;
import com.freded.task.entity.TaskQueryDTO;
import com.freded.task.entity.TaskSortAndPaginationDTO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
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
        System.out.println("Yes " +  currentUser);
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
