package com.myblog.blog.controller;

import com.myblog.blog.entity.blogQuery;
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

import java.util.List;

@Controller
public class CatalogShowController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/catalog/{id}")
    public String showCatalog(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC)
                                  @PathVariable Long id, Pageable pageable, Model model){

        List<catalog> catalogs = catalogService.listCatalogTop(10000);
        if (id == -1){
            id = catalogs.get(0).getId();
        }
        blogQuery blogQuery = new blogQuery();
        blogQuery.setCatalogId(id);
        model.addAttribute("catalogs", catalogs);
        model.addAttribute("page",pageable);
        model.addAttribute("activeId",id);

        return "catalog";
    }

}
