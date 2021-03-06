package com.myblog.blog.dao;

import com.myblog.blog.entity.blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<blog,Long> {

    blog findByTitle(String name);

    Page<blog> findAll(Specification<blog> blogSpecification, Pageable pageable);

    @Query("select b from blog b where b.recommend = true")
    List<blog> findTop(Pageable pageable);

    @Query("select b from blog b where b.title like ?1 or b.content like ?1 ")
    Page<blog> findByQuery(String query, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update blog b set b.views = b.views+1 where b.id=?1")
    int updateViews(Long id);
}
