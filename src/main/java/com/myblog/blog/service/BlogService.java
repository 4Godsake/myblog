package com.myblog.blog.service;

import com.myblog.blog.entity.blog;
import com.myblog.blog.entity.blogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

//    blog getBlog(String name);
    blog getBlog(Long id);
    Page<blog> listBlog(Pageable pageable);
    Page<blog> searchBlog(String query, Pageable pageable);
    Page<blog> searchBlog(Pageable pageable, blogQuery blog);
    List<blog> listRecommendBlogTop(Integer size);
    blog saveBlog(blog blog);
    blog updateBlog(Long id, blog blog);
    String getContent(Long id);
    void incViews(Long id);
    void deleteBlog(Long id);
}
