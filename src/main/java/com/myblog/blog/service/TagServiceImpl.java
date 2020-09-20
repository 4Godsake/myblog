package com.myblog.blog.service;

import com.myblog.blog.dao.TagRepository;
import com.myblog.blog.entity.tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public tag saveTag(tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public tag getTag(Long id) {
        return tagRepository.findById(id).get();
    }

    @Transactional
    @Override
    public tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public List<tag> listTags(String ids) {
        return tagRepository.findAllById(stringToList(ids));
    }

    private List<Long> stringToList(String ids) {
        List<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids != null){
            String[] idArray = ids.split(",");
            System.out.println(idArray+"fuck==========================================");
            for (int i=0; i < idArray.length; i++){
                list.add(new Long(idArray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public tag updateTag(Long id, tag tag) {
        tag tag1 = tagRepository.findById(id).get();
        BeanUtils.copyProperties(tag,tag1);
        return tagRepository.save(tag1);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
