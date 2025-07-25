package com.dio.springframeworkexperience.todo.mapper;

import com.dio.springframeworkexperience.todo.dto.UserCreationDTO;
import com.dio.springframeworkexperience.todo.dto.UserDTO;
import com.dio.springframeworkexperience.todo.dto.UserUpdateDTO;
import com.dio.springframeworkexperience.todo.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper mapper;

    public User toUser(UserDTO userDTO){
        return mapper.map(userDTO, User.class);
    }

    public UserDTO toUserDTO(User user){
        return mapper.map(user, UserDTO.class);
    }

    public User toUser(UserCreationDTO userCreationDTO){
        return mapper.map(userCreationDTO, User.class);
    }

    public UserDTO toUserDTO(UserCreationDTO userCreationDTO){
        return mapper.map(userCreationDTO, UserDTO.class);
    }

    public UserDTO toUserDTO(UserUpdateDTO userUpdateDTO){
        return mapper.map(userUpdateDTO, UserDTO.class);
    }
}
