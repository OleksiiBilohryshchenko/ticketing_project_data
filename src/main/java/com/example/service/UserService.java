package com.example.service;

import com.example.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO user);
    void deleteByUserName (String username);

}
