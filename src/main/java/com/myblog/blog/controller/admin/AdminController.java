package com.myblog.blog.controller.admin;

import com.myblog.blog.entity.*;
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

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogAdmin")
    public String blogAdmin(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                        Pageable pageable, Model model){
        model.addAttribute("catalogs", catalogService.listCatalog());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("blog", new blog());
        System.out.println("--------blogAdmin----------");
        return "admin/blogAdmin";
    }

    @GetMapping("/blogAdmin/{id}/edit")
    public String blogEdit(@PathVariable Long id,@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("catalogs", catalogService.listCatalog());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("page", blogService.listBlog(pageable));
        blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog", blog);
        model.addAttribute("fuck",blogService.getBlog(id).getCatalog().getId());
        // fffffffffffffffffffffffffuuuuuuuuuuuuuuuuuuuuuuuuuuuuucccccccccccccccccccccccccckkkkkkkkkkkkkkkkkkkkkkkkkkkkk
        System.out.println("--------blogEdit----------"+id);
        return "admin/blogAdmin";
    }

    @GetMapping("/blogAdmin/{id}/delete")
    public String blogDelete(@PathVariable Long id, RedirectAttributes attributes, HttpSession session){
        blog blog = blogService.getBlog(id);
        String blogTitle = blog.getTitle();
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("messageError","博客《"+blogTitle+"》"+"删除成功！");
        attributes.addFlashAttribute("flag",true);//redirect后在列表页面
        System.out.println("--------blogEdit----------"+id);
        return "redirect:/admin/blogAdmin";
    }

    @PostMapping("/blogAdmin/search")
    public String blogSearch(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                    Pageable pageable, blogQuery blog, Model model){
        model.addAttribute("page", blogService.searchBlog(pageable,blog));
        System.out.println("--------blogAdmin----------"+blog.getCatalogId());
        return "admin/blogAdmin :: blogList";
    }

    @PostMapping("/blogAdmin/blogsave")
    public String blogPost(blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((user) session.getAttribute("user"));
        blog.setCatalog(catalogService.getCatalog(blog.getCatalog().getId()));
        blog.setTags(tagService.listTags(blog.getTagIds()));
        blog blog1 =blogService.saveBlog(blog);
        if(blog1 == null){
            attributes.addFlashAttribute("messageError","发布失败");
        }else {
            if (blog1.getPublish()){
                attributes.addFlashAttribute("messageSuccess", "《"+blog1.getTitle()+"》"+"发布成功");
            }else{
                attributes.addFlashAttribute("messageSuccess", "《"+blog1.getTitle()+"》"+"保存成功");
            }

        }
        attributes.addFlashAttribute("flag",true);
        return "redirect:/admin/blogAdmin";
    }
}
