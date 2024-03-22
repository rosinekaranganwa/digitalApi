package com.example.digitalapi.register.controller;

import com.example.digitalapi.register.model.UserLogin;
import com.example.digitalapi.register.model.UserRegister;
import com.example.digitalapi.register.services.UserService;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserRegisterController {
    @Autowired
    private UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRegister> signUp(@RequestBody UserRegister user){
        UserRegister savedUser=userService.userRegister(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/signup")
    public ResponseEntity<List<UserRegister>> getAllUsers(){
        List<UserRegister> users=userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRegister> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllUsers(){
        userService.deleteAllUsers();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserRegister> login(@RequestBody UserLogin userLogin){
        return userService.login(userLogin.getEmailOrPhoneNumber(),userLogin.getPassword())
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.status(401).build());
    }

}

//@PostMapping("/login")
//public ResponseEntity<UserRegister> login(@RequestBody UserLoginRequest loginRequest) {
//    return userService.login(loginRequest.getEmailOrPhoneNumber(), loginRequest.getPassword())
//            .map(user -> ResponseEntity.ok(user))
//            .orElseGet(() -> ResponseEntity.status(401).build()); // Unauthorized if user not found or password mismatch
//}

