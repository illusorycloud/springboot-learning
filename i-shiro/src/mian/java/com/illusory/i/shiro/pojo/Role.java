package com.illusory.i.shiro.pojo;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 * @author illusory
 * 角色实体类
 */
@Data
public class Role {
    private Integer rid;
    private String rname;
    private Set<User> users = new HashSet<>();
    private Set<Permission> permissions = new HashSet<>();
}
