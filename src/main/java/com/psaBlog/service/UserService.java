package com.psaBlog.service;

import com.psaBlog.payload.UserDto;
import com.psaBlog.payload.UserResponse;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, String id);

    UserResponse getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    UserDto getUserById(String id);

    void deleteUser(String id);
}
