package com.myblog.blog.dao;

import com.myblog.blog.entity.tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<tag,Long> {

    tag findByName(String name);

    @Query("select t from tag t")
    List<tag> findTop(Pageable pageable);
}
