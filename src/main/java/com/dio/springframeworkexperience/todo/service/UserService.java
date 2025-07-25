package com.dio.springframeworkexperience.todo.service;

import com.dio.springframeworkexperience.todo.dto.UserCreationDTO;
import com.dio.springframeworkexperience.todo.dto.UserDTO;
import com.dio.springframeworkexperience.todo.dto.UserUpdateDTO;
import com.dio.springframeworkexperience.todo.mapper.UserMapper;
import com.dio.springframeworkexperience.todo.model.Role;
import com.dio.springframeworkexperience.todo.model.User;
import com.dio.springframeworkexperience.todo.repository.RoleRepository;
import com.dio.springframeworkexperience.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public UserDTO create(UserCreationDTO userCreationDTO){
        userCreationDTO.setPassword(passwordEncoder.encode(userCreationDTO.getPassword()));

        User user = userMapper.toUser(userCreationDTO);

        if (userCreationDTO.getRoles() != null && !userCreationDTO.getRoles().isEmpty()) {
            Set<Role> roles = userCreationDTO.getRoles().stream()
                    .map(transientRole -> roleRepository.findByName(transientRole.getName())
                            .orElseThrow(() -> new RuntimeException("Role not found: " + transientRole.getName())))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }

        return userMapper.toUserDTO(userRepository.save(user));
    }

    public UserDTO read(UserDTO userDTO){
        return userMapper.toUserDTO(readByUsername(userDTO));
    }

    @Transactional
    public UserDTO update(UserUpdateDTO userUpdateDTO){
        User user = readByUsername(userMapper.toUserDTO(userUpdateDTO));
        if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        }
        if(userUpdateDTO.getRoles() != null && !userUpdateDTO.getRoles().isEmpty()){
            Set<Role> roles = userUpdateDTO.getRoles().stream()
                    .map(transientRole -> roleRepository.findByName(transientRole.getName())
                            .orElseThrow(() -> new RuntimeException("Role not found: " + transientRole.getName())))
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
        return userMapper.toUserDTO(userRepository.save(user));
    }

    public void delete(UserDTO userDTO){
        userRepository.delete(readByUsername(userDTO));
    }

    private User readByUsername(UserDTO userDTO){
        return userRepository.findByUsername(
                userDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username " + userDTO.getUsername()));
    }
}
