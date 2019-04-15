package com.illusory.i.shiro.mapper;


import com.illusory.i.shiro.pojo.User;

import java.util.List;

public interface UserMapper {
    User findUserByName(String name);

    User findUserJustByName(String name);

    List<String> selectPermissionByUserId(Integer id);
}
