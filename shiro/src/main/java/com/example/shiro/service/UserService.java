package com.example.shiro.service;

import com.example.shiro.pojo.User;

public interface UserService {
    User findUserByName(String name);
}
