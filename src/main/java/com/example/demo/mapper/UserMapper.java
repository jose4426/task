package com.example.demo.mapper;

import com.example.demo.dto.ResponseRegister;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.UserEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse entityToResponse(UserEntity user);

    UserEntity requestToEntity(UserRequest request);
    ResponseRegister entityToResponseR(UserEntity user);

}
