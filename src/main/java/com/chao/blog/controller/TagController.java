package com.chao.blog.controller;

import com.chao.blog.entity.ResultCode;
import com.chao.blog.entity.ResultInfo;
import com.chao.blog.entity.Tag;
import com.chao.blog.service.TagService;
import com.chao.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping
    public ResultInfo<List<Tag>> getTags() {
        List<Tag> tags = tagService.getAllTag();
        return ResultUtil.build(ResultCode.SUCCESS, tags);
    }

}
