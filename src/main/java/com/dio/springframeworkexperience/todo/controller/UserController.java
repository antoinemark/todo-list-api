package com.dio.springframeworkexperience.todo.controller;

import com.dio.springframeworkexperience.todo.dto.UserCreationDTO;
import com.dio.springframeworkexperience.todo.dto.UserDTO;
import com.dio.springframeworkexperience.todo.dto.UserUpdateDTO;
import com.dio.springframeworkexperience.todo.service.UserService;
import jakarta.annotation.sql.DataSourceDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserCreationDTO userCreationDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userCreationDTO));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> read(@PathVariable String username){
        UserDTO userDTO = new UserDTO(username, null);
        return ResponseEntity.ok().body(userService.read(userDTO));
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserUpdateDTO userUpdateDTO){
        return ResponseEntity.ok().body(userService.update(userUpdateDTO));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> delete(@PathVariable String username){
        UserDTO userDTO = new UserDTO(username, null);
        userService.delete(userDTO);
        return ResponseEntity.noContent().build();
    }

}
