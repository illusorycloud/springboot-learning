package com.illusory.hello.mapper;

import com.illusory.hello.pojo.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
