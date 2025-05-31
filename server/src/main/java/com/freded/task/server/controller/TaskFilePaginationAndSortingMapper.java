package com.freded.task.server.controller;

import com.freded.dtos.TaskFileSortAndPaginationDTO;
import com.freded.task.client.dto.TaskFileQueryDTO;
import org.mapstruct.Mapper;

@Mapper()
public interface TaskFilePaginationAndSortingMapper {

    TaskFileSortAndPaginationDTO toTaskFilePaginationDTO(TaskFileQueryDTO taskFileQueryDTO);
}
