package com.freded.task.client.dto;

import com.freded.dtos.TaskFileSortAndPaginationDTO;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFileQueryDTO extends TaskFileSortAndPaginationDTO {
    @QueryParam("loadFiles")
    @DefaultValue("false")
    private boolean loadFiles;
}
