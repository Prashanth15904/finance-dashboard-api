package com.finance.dashboard.Controller;

import com.finance.dashboard.Dto.ResponseStructure;
import com.finance.dashboard.Dto.UserRequest;
import com.finance.dashboard.Entity.User;
import com.finance.dashboard.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityReturnValueHandler;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //Add user to the Database
    @PostMapping("/addUser")
    public ResponseEntity<ResponseStructure<User>> createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    //Fetch all Users
    @GetMapping("/users")
    public ResponseEntity<ResponseStructure<List<User>>> fetchUser(){
        return userService.fetchUser();
    }

    //Fetch User By Id
    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseStructure<User>> fetchUserById(@PathVariable Long id){
        return userService.fetchUserById(id);
    }

    //update User
    @PutMapping("/users/{id}")
    public ResponseEntity<ResponseStructure<User>> updateuser(@PathVariable Long id ,@RequestBody UserRequest userRequest){
        return userService.updateUser(id,userRequest);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
