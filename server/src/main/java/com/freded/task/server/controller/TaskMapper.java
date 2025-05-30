package com.freded.task.server.controller;

import com.freded.dtos.TaskDTO;
import com.freded.task.server.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(uses = {TaskFileMapper.class})
public interface TaskMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    TaskEntity toEntity(TaskDTO taskDTO);

    // Method that skips task files completely
    @Mapping(target = "taskFiles", ignore = true)
    @Named("withoutFiles")
    TaskDTO toDTOWithoutFiles(TaskEntity taskEntity);

    // Method that includes task files
    @Named("withFiles")
    TaskDTO toDTOWithFiles(TaskEntity taskEntity);


    List<TaskDTO> toDTOList(List<TaskEntity> taskEntityList);
}