package com.myblog.blog.dao;

import com.myblog.blog.entity.tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<tag,Long> {

    tag findByName(String name);
}
