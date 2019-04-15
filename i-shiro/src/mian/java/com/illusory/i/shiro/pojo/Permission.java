package com.illusory.i.shiro.pojo;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 * @author illusory
 * 权限实体类
 */
@Data
public class Permission {
    private Integer pid;
    private String permission;
    private Set<Role> roles = new HashSet<>();
}
