package com.psaBlog.controller;

import com.psaBlog.payload.UserDto;
import com.psaBlog.payload.UserResponse;
import com.psaBlog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Post
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto udto){
        UserDto user = userService.createUser(udto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto udto, @PathVariable String uid){
        UserDto userDto = userService.updateUser(udto, uid);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    //http://localhost:8080/user?pageNumber=0

    @GetMapping
    public ResponseEntity<UserResponse> getAllUser(
            @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        UserResponse allUser = userService.getAllUser(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<UserDto> getById(@PathVariable String uid){
        UserDto userById = userService.getUserById(uid);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable String uid){
        userService.deleteUser(uid);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.OK);

    }




}
