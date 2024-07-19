package com.psaBlog.service.impl;

import com.psaBlog.entity.User;
import com.psaBlog.exception.ResourceNotFound;
import com.psaBlog.payload.UserDto;
import com.psaBlog.payload.UserResponse;
import com.psaBlog.repository.UserRepository;
import com.psaBlog.service.UserService;
import org.apache.coyote.Request;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        String string = UUID.randomUUID().toString();
        user.setId(string);
        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User Not found with id: " + id));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserResponse getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(Sort.Direction.ASC, sortBy):Sort.by(Sort.Direction.DESC, sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> all = userRepository.findAll(pageRequest);
        List<User> content = all.getContent();


//        List<User> all = userRepository.findAll();
       List<UserDto> collect = content.stream().map(u -> modelMapper.map(u, UserDto.class)).collect(Collectors.toList());
        UserResponse userResponse = new UserResponse();
        userResponse.setUserDtoList(collect);
        userResponse.setPageNumber(all.getNumber());
        userResponse.setPageSize(all.getSize());
        userResponse.setTotalPage(all.getTotalPages());
        userResponse.setTotalElement(all.getTotalElements());
        userResponse.setLastPage(all.isLast());
        return userResponse;
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User Not found with id: " + id));

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFound("User Not found with id: " + id));
        userRepository.deleteById(id);


    }
}
