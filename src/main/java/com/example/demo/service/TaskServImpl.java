package com.example.demo.service;

import com.example.demo.config.Jwt;
import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.entity.TaskEntity;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskServImpl implements TaskServ {

    private final TaskMapper mapper;
    private final TaskRepository repository;
    final private Jwt jwt;


    @Override
    public List<TaskResponse> findAll(String token) {
        if (jwt.validateToken(token) == true) {
            List<TaskEntity> tasks = repository.findAll();
            return !tasks.isEmpty() ? tasks.stream().map(mapper::entityToResponse).collect(Collectors.toList()) : null;
        }throw new RuntimeException("token no valid");
    }
    @Override
    public TaskResponse findById(Long id) {

        if (Objects.nonNull(id)) {

            return mapper.entityToResponse(repository.findById(id).get());
        }
        throw new RuntimeException("no found id");
    }

    @Override
    public TaskResponse save(TaskRequest request ,String token) {
        if (jwt.validateToken(token) == true) {
            if (Objects.nonNull(request)) {

                TaskEntity task = TaskEntity.builder()
                        .state(request.getState())
                        .description(request.getDescription())
                        .build();

                return mapper.entityToResponse(repository.save(task));
            }
            throw new RuntimeException("no request");
        }else return null;
    }
    @Override
    public TaskResponse update(TaskRequest request) {
        Long id = request.getId();

        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo");
        }
        existsById(id);
        TaskEntity task = mapper.requestToEntity(request);

        return mapper.entityToResponse(repository.save(task));
    }


    @Override
    public void delete(Long id, String token) {
        if (jwt.validateToken(token) == true) {
            repository.deleteById(id);
        }
    }
    public void existsById(final Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("id no found");
        }
    }
}
