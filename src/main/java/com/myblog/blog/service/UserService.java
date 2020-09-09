package com.myblog.blog.service;

import com.myblog.blog.entity.user;

public interface UserService {
    user checkUser(String username,String password);
}
