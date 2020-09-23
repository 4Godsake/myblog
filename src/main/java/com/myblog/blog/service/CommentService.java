package com.myblog.blog.service;

import com.myblog.blog.entity.comment;

import java.util.List;

public interface CommentService {

    List<comment> listCommentByBlogId(Long blogId);

    comment saveComment(comment comment);
}
