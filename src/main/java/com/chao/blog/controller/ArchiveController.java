package com.chao.blog.controller;

import com.chao.blog.entity.ResultCode;
import com.chao.blog.entity.ResultInfo;
import com.chao.blog.entity.TimeLine;
import com.chao.blog.service.BlogService;
import com.chao.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/archive")
public class ArchiveController {

    @Autowired
    BlogService blogService;

    @RequestMapping
    public ResultInfo<Map<String, List<TimeLine>>> getTimeLine() {
        Map<String, List<TimeLine>> timeLine = blogService.getTimeLine();
        return ResultUtil.build(ResultCode.SUCCESS, timeLine);
    }
}
