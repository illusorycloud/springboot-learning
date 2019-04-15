package com.illusory.i.shiro.service.impl;
import com.illusory.i.shiro.mapper.UserMapper;
import com.illusory.i.shiro.pojo.User;
import com.illusory.i.shiro.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author illusory
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public User findUserJustByName(String name) {
        return userMapper.findUserJustByName(name);
    }

    @Override
    public List<String> selectPermissionByUserId(Integer id) {
        return userMapper.selectPermissionByUserId(id);
    }
}
