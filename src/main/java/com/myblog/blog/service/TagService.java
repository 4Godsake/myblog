package com.myblog.blog.service;

import com.myblog.blog.entity.catalog;
import com.myblog.blog.entity.tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    tag saveTag(tag tag);
    tag getTag(Long id);
    tag getTagByName(String name);
    Page<tag> listTag(Pageable pageable);
    List<tag> listTag();
    List<tag> listTag(String ids);
    tag updateTag(Long id,tag tag);
    void deleteTag(Long id);


}
