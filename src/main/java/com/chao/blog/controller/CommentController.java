package com.chao.blog.controller;

import com.chao.blog.entity.Comment;
import com.chao.blog.entity.validator.CommentValidator;
import com.chao.blog.entity.ResultCode;
import com.chao.blog.entity.ResultInfo;
import com.chao.blog.service.CommentService;
import com.chao.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @InitBinder
    public void validatorBinder(DataBinder dataBinder) {
        dataBinder.setValidator(new CommentValidator());
    }

    @GetMapping
    public ResultInfo<List<Comment>> getComment() {
        List<Comment> comments = commentService.getAllComment();
        return ResultUtil.build(ResultCode.SUCCESS, comments);
    }

    @GetMapping("/{blogId:\\d+}")
    public ResultInfo<List<Comment>> getCommentByBlogId(@PathVariable Long blogId) {
        List<Comment> comment = commentService.getCommentByBlogId(blogId);
        return ResultUtil.build(ResultCode.SUCCESS, comment);
    }

    @GetMapping("/total")
    public ResultInfo<Integer> getCommentTotal() {
        Integer total = commentService.getCommentTotal();
        return ResultUtil.build(ResultCode.SUCCESS, total);
    }

    @PostMapping
    public ResultInfo<String> addComment(@Validated @RequestBody Comment comment) {
        System.out.println(comment);
        boolean b = commentService.addComment(comment);
        return ResultUtil.build(b ? ResultCode.SUCCESS : ResultCode.PARAM_ERROR, "评论成功");
    }

}
