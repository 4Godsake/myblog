package com.myblog.blog.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_blog")
public class blog {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String picture;
    private String flag;//原创与否
    private Integer views;
    private Boolean reward;
    private Boolean copyright;//版权、转载声明
    private Boolean comment;
    private Boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @ManyToOne
    private catalog catalog;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<tag> tags = new ArrayList<>();
    @ManyToOne
    private user user;
    @OneToMany(mappedBy = "blog")
    private List<comment> comments = new ArrayList<>();


    public blog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Boolean getReward() {
        return reward;
    }

    public void setReward(Boolean reward) {
        this.reward = reward;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public Boolean getComment() {
        return comment;
    }

    public void setComment(Boolean comment) {
        this.comment = comment;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public com.myblog.blog.entity.catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(com.myblog.blog.entity.catalog catalog) {
        this.catalog = catalog;
    }

    public List<tag> getTags() {
        return tags;
    }

    public void setTags(List<tag> tags) {
        this.tags = tags;
    }

    public com.myblog.blog.entity.user getUser() {
        return user;
    }

    public void setUser(com.myblog.blog.entity.user user) {
        this.user = user;
    }

    public List<com.myblog.blog.entity.comment> getComments() {
        return comments;
    }

    public void setComments(List<com.myblog.blog.entity.comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", picture='" + picture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", reward=" + reward +
                ", copyright=" + copyright +
                ", comment=" + comment +
                ", recommend=" + recommend +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
