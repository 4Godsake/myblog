package com.myblog.blog.util;

import org.springframework.util.DigestUtils;

public class MD5Utils {

    public static void main(String[] args) {
        String md5Str = DigestUtils.md5DigestAsHex("6627221lt".getBytes());
        System.out.println(md5Str);
    }
}

