package com.example.shiro.mapper;

import com.example.shiro.pojo.User;

public interface UserMapper {
    User findUserByName(String name);
}
