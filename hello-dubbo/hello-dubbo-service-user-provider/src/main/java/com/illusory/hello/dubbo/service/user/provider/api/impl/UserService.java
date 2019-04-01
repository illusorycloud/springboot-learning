package com.illusory.hello.dubbo.service.user.provider.api.impl;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author Administrator
 * @version 1.0.0
 * @date 2019/4/1 0001
 */
@Service(version = "${user.service.version}")
public class UserService implements com.illusory.hello.dubbo.service.user.api.UserService {
    @Override
    public String sayHi() {
        return "Hello Dubbo!";
    }
}
