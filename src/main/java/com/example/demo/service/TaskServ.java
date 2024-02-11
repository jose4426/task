package com.example.demo.service;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskServ {

    List<TaskResponse> findAll(String token);

    TaskResponse findById(Long id);

    TaskResponse save(TaskRequest request, String token );
    TaskResponse update(TaskRequest request);

    void delete(Long id, String token);

}
