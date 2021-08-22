package com.chao.blog.controller;

import com.chao.blog.entity.Friend;
import com.chao.blog.entity.ResultCode;
import com.chao.blog.entity.ResultInfo;
import com.chao.blog.service.FriendService;
import com.chao.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping(params = "isRecommend=true")
    public ResultInfo<List<Friend>> getRecommendFriend() {
        List<Friend> recommends = friendService.getAllRecommend();
        return ResultUtil.build(ResultCode.SUCCESS, recommends);
    }

    @RequestMapping(params = "!isRecommend")
    public ResultInfo<List<Friend>> getFriend() {
        List<Friend> recommends = friendService.getAllFriend();
        return ResultUtil.build(ResultCode.SUCCESS, recommends);
    }

}
