package com.example.service.impl;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {

        List<User> userList = userRepository.findAll(Sort.by("firstName"));

        return userList.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        return userMapper.convertToDto(userRepository.findByUserName(username));
    }

    @Override
    public void save(UserDTO user) {
        userRepository.save(userMapper.convertToEntity(user));
    }

    @Override
    public void deleteByUserName(String username) {
        userRepository.deleteByUserName(username);
    }

    @Override
    public UserDTO update(UserDTO user) {

        //Find current user
        User user1 = userRepository.findByUserName(user.getUserName()); // has id

        //Map update user dto to entity object
        User convertedUser = userMapper.convertToEntity(user); // has id?

        //set id to converted object
        convertedUser.setId(user1.getId());

        //save updated user in the DB
        userRepository.save(convertedUser);
        return findByUserName(user.getUserName());


    }

    @Override
    public void delete(String username) {

        User user = userRepository.findByUserName(username);
        user.setIsDeleted(true);
        userRepository.save(user);

    }

}
