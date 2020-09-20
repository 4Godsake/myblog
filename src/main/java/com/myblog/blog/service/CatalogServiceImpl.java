package com.myblog.blog.service;

import com.myblog.blog.dao.CatalogRepository;
import com.myblog.blog.entity.catalog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService{

    @Autowired
    private CatalogRepository catalogRepository;


    //事务
    @Transactional
    @Override
    public catalog saveCatalog(catalog catalog) {
        return catalogRepository.save(catalog);
    }

    @Transactional
    @Override
    public catalog getCatalog(Long id) {
        return catalogRepository.findById(id).get();
    }

    @Override
    public catalog getCatalogByName(String name) {
        System.out.println(catalogRepository.findByName(name));
        return catalogRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<catalog> listCatalog(Pageable pageable) {
        return catalogRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<catalog> listCatalog() {
        return catalogRepository.findAll();
    }

    @Override
    public List<catalog> listCatalogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return catalogRepository.findListTop(pageable);
    }

    @Transactional
    @Override
    public catalog updateCatalog(Long id ,catalog catalog) {
        catalog c = catalogRepository.findById(id).get();
        BeanUtils.copyProperties(catalog,c);
        return catalogRepository.save(c);
    }

    @Transactional
    @Override
    public void deleteCatalog(Long id) {
        catalogRepository.deleteById(id);
    }
}
