package com.myblog.blog.controller.admin;

import com.myblog.blog.entity.tag;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TagController {
    //标签部分-----------------------------------------------------------------------------------------------------------
    @Autowired
    private TagService tagService;

    @GetMapping("/admin/tagAdmin")
    public String tagAdmin(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                   Pageable pageable, Model model){

        model.addAttribute("page", tagService.listTag(pageable));
        System.out.println("--------tagAdmin----------");
        return "admin/tagAdmin";
    }

    //增加标签
    @PostMapping("/admin/tagAdmin")
    public String addTag(tag tag, RedirectAttributes attributes){
        System.out.println("new");
        tag tt = tagService.getTagByName(tag.getName());
        if (tt!=null){
            attributes.addFlashAttribute("messageError","新增失败：标签已存在");
            return "redirect:/admin/tagAdmin";
        }
        tag tag1 = tagService.saveTag(tag);
        if (tag1 == null){
            attributes.addFlashAttribute("messageError","新增失败");
        }else {
            attributes.addFlashAttribute("messageSuccess","新增成功");
        }
        return "redirect:/admin/tagAdmin";
    }

    //编辑标签
    @PostMapping("/admin/tagAdmin/{id}")
    public String editTag(tag tag, @PathVariable Long id, RedirectAttributes attributes){

        tag t = tagService.getTagByName(tag.getName());
        if (t!=null){
            attributes.addFlashAttribute("messageError","更新失败：标签已存在");
            return "redirect:/admin/tagAdmin";
        }
        tag tag1 = tagService.updateTag(id,tag);
        if (tag1 == null){
            attributes.addFlashAttribute("messageError","更新失败");
        }else {
            attributes.addFlashAttribute("messageSuccess","更新成功");
        }
        return "redirect:/admin/tagAdmin";
    }

    //删除标签
    @GetMapping("/admin/tagAdmin/{id}/delete")
    public String deleteTag(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("messageSuccess","删除成功");
        System.out.println("--------deleteTag----------");
        return "redirect:/admin/tagAdmin";
    }

}
