package com.chao.blog.controller;

import com.chao.blog.entity.ResultCode;
import com.chao.blog.entity.ResultInfo;
import com.chao.blog.entity.Type;
import com.chao.blog.service.TypeService;
import com.chao.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping
    public ResultInfo<List<Type>> getTypes() {
        List<Type> types = typeService.getAllType();
        return ResultUtil.build(ResultCode.SUCCESS, types);
    }

}
