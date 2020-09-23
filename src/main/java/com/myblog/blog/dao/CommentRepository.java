package com.myblog.blog.dao;

import com.myblog.blog.entity.comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<comment,Long> {


    List<comment> findByBlogId(Long blogId, Sort sort);


}
