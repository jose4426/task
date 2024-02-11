package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    private Long id;
    private  String name;
    private String lastname;
    @Column(unique = true)
    private String email;
    private  String password;
}
