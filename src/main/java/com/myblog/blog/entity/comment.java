package com.myblog.blog.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_comment")
public class comment {
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private blog blog;
    @OneToMany(mappedBy = "parentComment")
    private List<comment> replyComments = new ArrayList<>();
    @ManyToOne
    private comment parentComment;

    public comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public com.myblog.blog.entity.blog getBlog() {
        return blog;
    }

    public void setBlog(com.myblog.blog.entity.blog blog) {
        this.blog = blog;
    }

    public List<comment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<comment> replyComments) {
        this.replyComments = replyComments;
    }

    public comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(comment parentComment) {
        this.parentComment = parentComment;
    }

    @Override
    public String toString() {
        return "comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
