package com.example.digitalapi.register.services;

import com.example.digitalapi.register.model.UserRegister;
import com.example.digitalapi.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserRegister userRegister(UserRegister register){
        return userRepository.save(register);
    }

    public List<UserRegister> getUsers(){
        return userRepository.findAll();
    }

    public Optional<UserRegister> getUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
    public void deleteAllUsers(){
        userRepository.deleteAll();
    }

    public Optional<UserRegister> login(String emailOrPhoneNumber,String password){
        return userRepository.findAll().stream().filter(user ->
                password.equals(user.getPassword()) && (emailOrPhoneNumber.equals(user.getEmail()) || emailOrPhoneNumber.equals(user.getPhoneNumber())))
                .findFirst();
    }


}
