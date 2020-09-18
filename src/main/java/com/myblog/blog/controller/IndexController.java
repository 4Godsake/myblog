package com.myblog.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){

        System.out.println("--------index----------");
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blogInfo(@PathVariable Long id, Model model){

        System.out.println("--------blogInfo----------");
        return "blogInfo";
    }
    @GetMapping("/catalog")
    public String catalog(){

        System.out.println("--------catalog----------");
        return "catalog";
    }
    @GetMapping("/tag")
    public String tag(){

        System.out.println("--------tag----------");
        return "tag";
    }
}


