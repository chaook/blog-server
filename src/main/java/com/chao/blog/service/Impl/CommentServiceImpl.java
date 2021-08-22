package com.chao.blog.service.Impl;

import com.chao.blog.entity.Comment;
import com.chao.blog.mapper.CommentMapper;
import com.chao.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public Comment getCommentById(Long coId) {
        return commentMapper.getCommentById(coId);
    }

    @Override
    public List<Comment> getAllComment() {
        List<Comment> comments = commentMapper.getAllComment();
        return setChildComments(comments);
    }

    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        List<Comment> comments = commentMapper.getCommentByBlogId(blogId);
        return setChildComments(comments);
    }

    @Override
    public List<Comment> getCommentByParentId(Long parentId) {
        return commentMapper.getCommentByParentId(parentId);
    }

    @Override
    public Integer getCommentTotal() {
        return commentMapper.getCommentTotal();
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public boolean deleteCommentById(Long coId) {
        return commentMapper.deleteCommentById(coId);
    }

    @Override
    public boolean deleteAllComment() {
        return commentMapper.deleteAllComment();
    }

    @Override
    public boolean updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }

    /**
     * 设置 Comment 所有子孙评论，childComments 按照创建时间排序，先创建的在前面
     @param comments 需要设置的 Comment 列表

     @return 带有子孙评论的 Comment 列表
     */
    private List<Comment> setChildComments(List<Comment> comments) {
        for (Comment c : comments) {
            List<Comment> childComments = getAllChildComment(c.getCoId());
            childComments.sort(Comparator.comparing(Comment::getCreateTime));
            //等价于下面的代码，按创建时间排序
            /*childComments.sort(new Comparator<Comment>() {
                @Override
                public int compare(Comment o1, Comment o2) {
                    return o1.getCreateTime().compareTo(o2.getCreateTime());
                }
            });*/
            c.setChildComments(childComments);
        }
        return comments;
    }

    private List<Comment> getAllChildComment(Long coId) {
        List<Comment> comments = new ArrayList<>();
        Queue<Long> parentIds = new ArrayDeque<>();
        parentIds.add(coId);
        while (parentIds.size() > 0) {
            Long parentId = parentIds.poll();
            List<Comment> temComment = commentMapper.getCommentByParentId(parentId);
            Comment parentComment = commentMapper.getCommentById(parentId);
            for (Comment c : temComment) {
                parentIds.add(c.getCoId());
                c.setParentComment(parentComment);
            }
            comments.addAll(temComment);
        }
        return comments;
    }
}
