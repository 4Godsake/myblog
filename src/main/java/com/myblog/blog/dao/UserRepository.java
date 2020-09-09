package com.myblog.blog.dao;

import com.myblog.blog.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user,Long> {
    user findByUsernameAndPassword(String username, String password);
}
