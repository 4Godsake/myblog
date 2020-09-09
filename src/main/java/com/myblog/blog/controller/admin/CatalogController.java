package com.myblog.blog.controller.admin;

import com.myblog.blog.entity.catalog;
import com.myblog.blog.service.CatalogService;
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
public class CatalogController {
    //分类部分-----------------------------------------------------------------------------------------------------------
    @Autowired
    private CatalogService catalogService;

    @GetMapping("/admin/catalogAdmin")
    public String catalogAdmin(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                       Pageable pageable, Model model){

        model.addAttribute("page", catalogService.listCatalog(pageable));
        System.out.println("--------catalogAdmin----------");
        return "admin/catalogAdmin";
    }
    //增加分类
    @PostMapping("/admin/catalogAdmin")
    public String addCatalog(catalog catalog, RedirectAttributes attributes){
        System.out.println("new");
        catalog cc = catalogService.getCatalogByName(catalog.getName());
        if (cc!=null){
            attributes.addFlashAttribute("messageError","新增失败：分类已存在");
            return "redirect:/admin/catalogAdmin";
        }
        catalog catalog1 = catalogService.saveCatalog(catalog);
        if (catalog1 == null){
            attributes.addFlashAttribute("messageError","新增失败");
        }else {
            attributes.addFlashAttribute("messageSuccess","新增成功");
        }
        return "redirect:/admin/catalogAdmin";
    }
    //编辑分类
    @PostMapping("/admin/catalogAdmin/{id}")
    public String editCatalog(catalog catalog, @PathVariable Long id, RedirectAttributes attributes){

        catalog c = catalogService.getCatalogByName(catalog.getName());
        if (c!=null){
            attributes.addFlashAttribute("messageError","更新失败：分类已存在");
            return "redirect:/admin/catalogAdmin";
        }
        catalog catalog1 = catalogService.updateCatalog(id,catalog);
        if (catalog1 == null){
            attributes.addFlashAttribute("messageError","更新失败");
        }else {
            attributes.addFlashAttribute("messageSuccess","更新成功");
        }
        return "redirect:/admin/catalogAdmin";
    }

    //删除分类
    @GetMapping("/admin/catalogAdmin/{id}/delete")
    public String deleteCatalog(@PathVariable Long id,RedirectAttributes attributes){
        catalogService.deleteCatalog(id);
        attributes.addFlashAttribute("messageSuccess","删除成功");
        System.out.println("--------deleteCatalog----------");
        return "redirect:/admin/catalogAdmin";
    }


}
