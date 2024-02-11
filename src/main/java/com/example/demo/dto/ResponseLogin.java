package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ResponseLogin {
    private String name;
    private String lastname;
    private String email;
    private String token;

}
