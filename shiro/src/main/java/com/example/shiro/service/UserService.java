package com.example.shiro.service;

import com.example.shiro.pojo.User;

import java.util.List;

public interface UserService {
    User findUserByName(String name);
    User findUserJustByName(String name);
    List<String> selectPermissionByUserId(Integer id);
}
