package com.myblog.blog.dao;

import com.myblog.blog.entity.catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<catalog,Long> {

    catalog findByName(String name);
}
