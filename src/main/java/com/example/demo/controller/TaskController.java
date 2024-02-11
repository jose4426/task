package com.example.demo.controller;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.service.TaskServ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/task")
@CrossOrigin(origins = "http://localhost:8081")
public class TaskController {
    private final TaskServ service;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskResponse>> getAll(@RequestHeader( value ="Authorization") String token) {

        List<TaskResponse> response = service.findAll(token);

        return ResponseEntity.ok(response);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponse> add(@RequestHeader( value ="Authorization") String token, @RequestBody TaskRequest request) {
        TaskResponse response = service.save(request,token);
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponse> getById(@PathVariable("id") Long id) {

        TaskResponse response = service.findById(id);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") Long id, @RequestHeader( value ="Authorization") String token ) {

      service.delete(id, token);

    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponse> update(@RequestBody TaskRequest request) {
        TaskResponse response = service.update(request);
        return ResponseEntity.ok(response);
    }

}
