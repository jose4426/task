package com.example.demo.dto;

import com.example.demo.entity.TaskEntity;
import lombok.*;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String password;
    private List<TaskEntity> task;
}
