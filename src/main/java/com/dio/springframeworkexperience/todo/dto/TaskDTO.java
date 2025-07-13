package com.dio.springframeworkexperience.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDTO {
    private Long id;

    private String title;

    private String description;

    private LocalDateTime deadline;

    private boolean finished;
}
