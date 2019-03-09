package com.example.shiro.mapper;

import com.example.shiro.pojo.User;

import java.util.List;
public interface UserMapper {
    User findUserByName(String name);
    User findUserJustByName(String name);

    List<String> selectPermissionByUserId(Integer id);
}
