package com.example.demo.service;

import com.example.demo.config.Jwt;
import com.example.demo.dto.*;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserServImpl implements UserServ {
    private final UserRepository repository;
    private final UserMapper mapper;
    final private PasswordEncoder passwordEncoder;
    private final Jwt jwt;

    @Override
    public List<UserResponse> findAll() {

        List<UserEntity> users = repository.findAll();
        return !users.isEmpty() ? users.stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList()) : null;

    }

    @Override
    public UserResponse save(UserRequest request) {
        if (Objects.nonNull(request)) {

            UserEntity user = mapper.requestToEntity(request);

            return mapper.entityToResponse(repository.save(user));
        }
        throw new RuntimeException("no request");
    }

    @Override
    public UserResponse findById(Long id) {

        if (Objects.nonNull(id)) {

            return mapper.entityToResponse(repository.findById(id).get());
        }
        throw new RuntimeException("no id");
    }

    @Override
    public UserResponse update(UserRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {
        if (Objects.nonNull(id)) {
            repository.deleteById(id);

        }
        throw new RuntimeException("no id");
    }

    @Override
    public ResponseLogin validateLogin(LoginRequest request) {
        var user = findByEmail(request.getEmail());

        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseLogin.builder()
                    .token(jwt.create(String.valueOf(user.getId()), user.getName(), user.getEmail()))
                    .email(user.getEmail())
                    .lastname(user.getLastname())
                    .name(user.getName())
                    .build();
        } else {
            throw new RuntimeException("User not valid");
        }

    }

    @Override
    public ResponseRegister register(UserRequest request) {
        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        return mapper.entityToResponseR(repository.save(user));
    }

    @Override
    public UserResponse findByEmail(String email) {
        UserEntity user = repository.findByEmail(email).get();
        return mapper.entityToResponse(user);
    }
}
