package com.example.shiro.hash;


import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import sun.security.provider.MD5;

public class MD5Demo {
    @Test
    public void testMD5(){
    Md5Hash md5Hash = new Md5Hash("qwer");
        System.out.println(md5Hash);
//        962012d09b8170d912f0669f6d7d9d07
    }
}
