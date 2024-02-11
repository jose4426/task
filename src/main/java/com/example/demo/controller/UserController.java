package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.UserServ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserServ service;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponse>> getAll() {

        List<UserResponse> response = service.findAll();

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> add(@RequestBody UserRequest request) {
        UserResponse response = service.save(request);
        if (Objects.nonNull(response)){
            return ResponseEntity.ok(response);
        }
        throw new RuntimeException("token no validate");
    }
    //@Operation(summary = "User mobil login")
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    ResponseLogin UserLogin(@RequestBody LoginRequest request) {


        return service.validateLogin(request);

    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseRegister saveUser(@RequestBody UserRequest request) {

        ResponseRegister response = service.register(request);
        if (Objects.nonNull(response)){
            return response;
        }
        throw new RuntimeException("token no validate");
    }
}
