package com.myblog.blog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_catalog")
public class catalog {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "catalog")
    private List<blog> blogs = new ArrayList<>();


    public catalog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "catalog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
