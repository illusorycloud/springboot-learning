package com.illusory.hello.service.Impl;

import com.illusory.hello.mapper.UserMapper;
import com.illusory.hello.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional //事务
public class UserService implements com.illusory.hello.service.UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    /**
     * 根据Id查询用户
     *
     * @param id
     * @return
     */
    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    /**
     * 更新用户
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 根据ID删除用户
     *
     * @param id
     */
    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }
}
