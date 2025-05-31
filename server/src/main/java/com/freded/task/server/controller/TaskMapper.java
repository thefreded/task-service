package com.freded.task.server.controller;

import com.freded.dtos.TaskDTO;
import com.freded.task.server.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper()
public interface TaskMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    TaskEntity toEntity(TaskDTO taskDTO);

    TaskDTO toDTO(TaskEntity taskEntity);

    List<TaskDTO> toDTOList(List<TaskEntity> taskEntityList);
}