package com.myblog.blog.service;

import com.myblog.blog.dao.CommentRepository;
import com.myblog.blog.entity.comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{



    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        List<comment> comments= commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public comment saveComment(comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1){
            comment.setParentComment(commentRepository.findById(parentCommentId).get());
        }else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }


    /**
     * 循环每个定级的评论节点
     */
    private List<comment> eachComment(List<comment> comments) {
        List<comment> commentsView = new ArrayList<>();
        for (comment comment : comments){
            comment c = new comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     * comments root根节点，blog不为空的对象集合
     */
    private void combineChildren(List<comment> comments){

        for (comment comment : comments){
            List<comment> replys1 = comment.getReplyComments();
            for (comment reply1 : replys1){
                //循环找出子代回复，并存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清楚临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<comment> tempReplys = new ArrayList<>();

    /**
     * 递归迭代，剥洋葱
     * comment 被迭代的对象
     */
    private void recursively(comment comment) {
        tempReplys.add(comment);
        if (comment.getReplyComments().size()>0) {
            List<comment> replys = comment.getReplyComments();
            for (comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    recursively(reply);
                }
            }
        }
    }


}



