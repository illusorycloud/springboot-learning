package com.illusory.i.shiro.pojo;


import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 * @author illusory
 * 用户实体类
 */
@Data
public class User {
    private Integer uid;
    private String uname;
    private String upwd;
    private String salt;
    private Set<Role> roles=new HashSet<>();
}
