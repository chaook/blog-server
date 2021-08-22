package com.chao.blog.service;

import com.chao.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment getCommentById(Long coId);

    List<Comment> getAllComment();

    List<Comment> getCommentByBlogId(Long blogId);

    List<Comment> getCommentByParentId(Long parentId);

    Integer getCommentTotal();

    boolean addComment(Comment comment);

    boolean deleteCommentById(Long coId);

    boolean deleteAllComment();

    boolean updateComment(Comment comment);


}
