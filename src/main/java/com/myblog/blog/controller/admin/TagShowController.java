package com.myblog.blog.controller.admin;

import com.myblog.blog.entity.blogQuery;
import com.myblog.blog.entity.tag;
import com.myblog.blog.service.BlogService;
import com.myblog.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tag/{id}")
    public String showTag(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)
                              @PathVariable Long id, Pageable pageable, Model model){

        List<tag> tags = tagService.listTagTop(10000);
        if (id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page",blogService.searchBlog(pageable,id));
        model.addAttribute("activeId",id);

        return "tag";
    }
}
