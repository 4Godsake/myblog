package com.myblog.blog.controller;

import com.myblog.blog.entity.comment;
import com.myblog.blog.service.BlogService;
import com.myblog.blog.service.CatalogService;
import com.myblog.blog.service.CommentService;
import com.myblog.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {


    @Autowired
    private BlogService blogService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("catalogs",catalogService.listCatalogTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(6));
        System.out.println("--------index----------");
        return "home";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    @RequestParam String query, Pageable pageable, Model model){
        model.addAttribute("catalogs",catalogService.listCatalogTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(6));
        model.addAttribute("page",blogService.searchBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        System.out.println("--------blogSearch----------");
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blogInfo(@PathVariable Long id, Model model){
        model.addAttribute("blog",blogService.getBlog(id));
        model.addAttribute("content",blogService.getContent(id));
        model.addAttribute("comments",commentService.listCommentByBlogId(id));
        blogService.incViews(id);
        System.out.println("--------blogInfo----------");
        return "blog";
    }

}


