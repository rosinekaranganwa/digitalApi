package com.example.digitalapi.register.repository;

import com.example.digitalapi.register.model.UserRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRegister,Long> {
}

