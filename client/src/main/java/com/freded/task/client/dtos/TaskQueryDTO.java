package com.freded.task.client.dtos;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskQueryDTO {
    @QueryParam("loadFiles")
    @DefaultValue("false")
    private boolean loadFiles;
}
