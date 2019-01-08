package com.illusory.demodao.mapper;

import com.illusory.demodao.pojo.User;

import java.util.List;

/**
 * Mapper
 */
public interface UserMapper {
    void addUser(User user);
    List<User> queryAll();
    User findUserById(Integer id);
    void updateUser(User user);
    void deleteUserById(Integer id);
}
