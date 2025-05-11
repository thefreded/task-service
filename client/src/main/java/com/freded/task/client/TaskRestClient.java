package com.freded.task.client;

import com.freded.auth.DynamicAuthHeadersFactory;
import com.freded.task.client.boundary.Task;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(configKey = "task-api")
@RegisterClientHeaders(DynamicAuthHeadersFactory.class)
public interface TaskRestClient extends Task {
}
