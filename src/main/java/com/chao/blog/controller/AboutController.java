package com.chao.blog.controller;

import com.chao.blog.entity.About;
import com.chao.blog.entity.ResultCode;
import com.chao.blog.entity.ResultInfo;
import com.chao.blog.service.AboutService;
import com.chao.blog.utils.ResultUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/about")
public class AboutController {

    @Autowired
    AboutService aboutService;

    @GetMapping
    public ResultInfo<About> getAbout() {
        About about = aboutService.getAbout();
        return ResultUtil.build(ResultCode.SUCCESS, about);
    }

    @PostMapping
    public ResultInfo<Void> addAbout(@RequestBody About about) {
        Integer total = aboutService.getAboutTotal();
        if (total > 1) {
            return ResultUtil.build(ResultCode.PARAM_ERROR);
        }
        boolean b = aboutService.addAbout(about);
        return ResultUtil.build(b ? ResultCode.SUCCESS : ResultCode.PARAM_ERROR);
    }

    @PutMapping
    public ResultInfo<Void> updateAbout(@RequestBody About about) {
        Integer total = aboutService.getAboutTotal();
        if (total != 1) {
            return ResultUtil.build(ResultCode.PARAM_ERROR);
        }
        boolean b = aboutService.updateAbout(about);
        return ResultUtil.build(b ? ResultCode.SUCCESS : ResultCode.PARAM_ERROR);
    }

    @DeleteMapping
    public ResultInfo<Void> deleteAbout() {
        boolean b = aboutService.deleteAbout();
        return ResultUtil.build(b ? ResultCode.SUCCESS : ResultCode.PARAM_ERROR);
    }

}
