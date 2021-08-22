package com.chao.blog.entity.validator;

import com.chao.blog.entity.Comment;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
public class CommentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Comment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Comment comment = (Comment) o;
        if (comment.getName().isEmpty()) {
            errors.reject("error","昵称不能为空");
        } else if (comment.getContent().isEmpty()) {
            errors.reject("error","评论内容不能为空");
        } else if (comment.getBlogId()==null || comment.getBlogId()==0) {
            errors.reject("error","请求错误参数，blogId 错误");
        }
    }
}
