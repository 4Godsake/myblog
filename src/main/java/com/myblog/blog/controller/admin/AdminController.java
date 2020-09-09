package com.myblog.blog.controller.admin;

import com.myblog.blog.entity.blog;
import com.myblog.blog.entity.blogQuery;
import com.myblog.blog.entity.catalog;
import com.myblog.blog.entity.tag;
import com.myblog.blog.service.BlogService;
import com.myblog.blog.service.CatalogService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/blogAdmin")
    public String blogAdmin(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                        Pageable pageable, Model model){
        model.addAttribute("catalogs", catalogService.listCatalog());
        model.addAttribute("page", blogService.listBlog(pageable));
        System.out.println("--------blogAdmin----------");
        return "admin/blogAdmin";
    }

    @PostMapping("/blogAdmin/search")
    public String blogSearch(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable, blogQuery blog, Model model){
        model.addAttribute("page", blogService.searchBlog(pageable,blog));
        System.out.println("--------blogAdmin----------"+blog.getCatalogId());
        return "admin/blogAdmin :: bloglist";
    }

}
