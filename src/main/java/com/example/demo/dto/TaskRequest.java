package com.example.demo.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskRequest {
    private long id;
    private String state;
    private String description;

}
