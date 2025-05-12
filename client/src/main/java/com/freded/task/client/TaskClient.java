package com.freded.task.client;


import com.freded.dtos.TaskDTO;
import com.freded.entities.TaskEntity;
import com.freded.task.client.entity.TaskQueryDTO;
import com.freded.task.client.entity.TaskSortAndPaginationDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class TaskClient {

    @Inject
    @RestClient
    TaskRestClient taskRestClient;


    public List<TaskDTO> getAll(final TaskSortAndPaginationDTO qParams) {
        return taskRestClient.getAll(qParams);
    }

    public TaskDTO create(final TaskDTO task) {
        return taskRestClient.create(task);

    }

    public TaskDTO get(final String taskId, final TaskQueryDTO taskParams) {
        return taskRestClient.get(taskId, taskParams);
    }

    public TaskEntity get(final String taskId, final Boolean returnEntity) {
        return taskRestClient.get(taskId, returnEntity);
    }

    public String delete(final String taskId) {
        return taskRestClient.delete(taskId);
    }

    public TaskDTO update(final String taskId, final TaskDTO task) {
        return taskRestClient.update(taskId, task);
    }
}
