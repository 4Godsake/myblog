package com.myblog.blog.service;

import com.myblog.blog.dao.BlogRepository;
import com.myblog.blog.entity.blog;
import com.myblog.blog.entity.blogQuery;
import com.myblog.blog.entity.catalog;
import com.myblog.blog.util.MyBeanUtils;
import com.myblog.blog.util.mdConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

//    @Transactional
//    @Override
//    public blog getBlog(String name) {
//        return blogRepository.findByTitle(name);
//    }

    @Transactional
    @Override
    public blog getBlog(Long id) {
        return blogRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Page<blog> searchBlog(Pageable pageable, blogQuery blog) {
        return blogRepository.findAll(new Specification<blog>() {
            @Override
            public Predicate toPredicate(Root<com.myblog.blog.entity.blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle()!=null){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"), "%"+blog.getTitle()+"%"));
                }
                if (blog.getCatalogId()!=null){
                    predicates.add(criteriaBuilder.equal(root.<catalog>get("catalog").get("id"),blog.getCatalogId()));
                }
                if(blog.getRecommend()!=null && blog.getRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),blog.getRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<blog> searchBlog(Pageable pageable, Long tagId) {
        return blogRepository.findAll(new Specification<blog>() {
            @Override
            public Predicate toPredicate(Root<blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Join join = root.join("tags");
                return criteriaBuilder.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public List<blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public Page<blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<blog> searchBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query, pageable);
    }

    @Transactional
    @Override
    public blog saveBlog(blog blog) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public blog updateBlog(Long id, blog blog) {
        blog blog1 = blogRepository.findById(id).get();
        BeanUtils.copyProperties(blog,blog1, MyBeanUtils.getNullPropertyNames(blog));
        blog1.setUpdateTime(new Date());
        return blogRepository.save(blog1);
    }

    @Override
    /* 将Markdown格式内容转化为html标签 */
    public String getContent(Long id) {
        String content = blogRepository.findById(id).get().getContent();
        return mdConvertUtils.mdToHTML(content);
    }
    /* 增加浏览次数 */
    @Override
    public void incViews(Long id) {
        blogRepository.updateViews(id);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
