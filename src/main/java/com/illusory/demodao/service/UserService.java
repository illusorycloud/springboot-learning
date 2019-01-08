package com.illusory.demodao.service;

import com.illusory.demodao.pojo.User;

import java.util.List;

/**
 * Service
 */
public interface UserService {
    void addUser(User user);
    List<User> queryAll();
    User findUserById(Integer id);
    void updateUser(User user);
    void deleteUserById(Integer id);
}
