package com.myblog.blog.service;

import com.myblog.blog.entity.catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogService {

    catalog saveCatalog(catalog catalog);
    catalog getCatalog(Long id);
    catalog getCatalogByName(String name);
    Page<catalog> listCatalog(Pageable pageable);
    List<catalog> listCatalog();
    catalog updateCatalog(Long id,catalog catalog);
    void deleteCatalog(Long id);

}
