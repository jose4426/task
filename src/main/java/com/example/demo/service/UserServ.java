package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServ {
    List<UserResponse> findAll();

    UserResponse save(UserRequest request);

    UserResponse findById(Long id);

    UserResponse update(UserRequest request);

    void delete(Long id);

    ResponseLogin validateLogin(LoginRequest request);
    ResponseRegister register(UserRequest request);
    UserResponse findByEmail(String email);
}
