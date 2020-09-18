package com.myblog.blog.entity;


import org.junit.Test;

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

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    private String picture;
    private String flag;//原创与否
    private Integer views;
    private Boolean reward;//开启赞赏
    private Boolean copyright;//版权、转载声明
    private Boolean comment;//开启评论
    private Boolean recommend;//是否推荐
    private Boolean publish;
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

    @Transient
    private String tagIds;

    private String synopsis;

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

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

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
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

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }


    public void init(){
        this.tagIds = tagsToIds(this.getTags());
        System.out.println(tagIds);
    }

    private String tagsToIds(List<tag> tags) {
        if(!tags.isEmpty()){
            StringBuffer ids =new StringBuffer();
            boolean flag = false;
            for(tag tag : tags) {
                if (flag){
                    ids.append(",");
                }else{
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else{
            return tagIds;
        }

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
                ", publish=" + publish +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", catalog=" + catalog +
                ", tags=" + tags +
                ", user=" + user +
                ", comments=" + comments +
                ", tagIds='" + tagIds + '\'' +
                '}';
    }
}
