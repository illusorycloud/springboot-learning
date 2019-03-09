package com.example.shiro.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;

public class MD5Test {
    static final String SALT = "illusory";
    static final String PWD = "qwer";

    @Test
    public void hash() {
        Md5Hash md5Hash1 = new Md5Hash(PWD);
        Md5Hash md5Hash2 = new Md5Hash(PWD, SALT);
        //6aee9c0e35ad7a12e59ff67b663a32ca
        System.out.println(md5Hash1);
        System.out.println(md5Hash2);
    }
}
