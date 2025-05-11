package com.freded.task.client;


import com.freded.dtos.TaskDTO;
import com.freded.entities.TaskEntity;
import com.freded.task.client.entity.TaskQueryDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class TaskClient {

    @Inject
    @RestClient
    TaskRestClient taskRestClient;


    public TaskDTO create(final TaskDTO task) {
        return taskRestClient.create(task);

    }

    public TaskDTO get(final String taskId, final TaskQueryDTO taskParams) {
        return taskRestClient.get(taskId, taskParams);
    }

    public TaskEntity get(final String taskId, final Boolean returnEntity) {
        return taskRestClient.get(taskId, returnEntity);
    }
}
