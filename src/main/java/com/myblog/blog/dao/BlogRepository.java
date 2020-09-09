package com.myblog.blog.dao;

import com.myblog.blog.entity.blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<blog,Long> {

    blog findByTitle(String name);

    Page<blog> findAll(Specification<blog> blogSpecification, Pageable pageable);
}
