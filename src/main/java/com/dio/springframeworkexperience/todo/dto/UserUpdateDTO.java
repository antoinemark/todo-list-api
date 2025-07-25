package com.dio.springframeworkexperience.todo.dto;

import com.dio.springframeworkexperience.todo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private String username;

    private Set<Role> roles;

    private String password;
}
