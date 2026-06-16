package com.kaua.testebackend.service;

import com.kaua.testebackend.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    User create(User user);
    User update(Long id, User user);
    void delete(Long id);

}
