package com.myblog.blog.dao;

import com.myblog.blog.entity.catalog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatalogRepository extends JpaRepository<catalog,Long> {

    catalog findByName(String name);

    @Query("select c from catalog c")
    List<catalog> findListTop(Pageable pageable);
}
