package com.gtech.demo.repository;

import java.util.List;

import com.gtech.demo.entity.Product;
import com.gtech.demo.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUsernameAndPassword(String username, String password);
    List<User> findByLogintoken(String token);
    List<User> findByLastname(String lastname);
    List<User> findByEmail(String email);
    List<User> findByResetpassToken(String resetpassToken);
}