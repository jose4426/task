package com.example.demo.mapper;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.TaskEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponse entityToResponse(TaskEntity entity);

    TaskEntity requestToEntity(TaskRequest request);

}
