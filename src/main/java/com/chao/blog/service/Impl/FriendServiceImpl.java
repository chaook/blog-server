package com.chao.blog.service.Impl;

import com.chao.blog.entity.Friend;
import com.chao.blog.mapper.FriendMapper;
import com.chao.blog.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;

    @Override
    public List<Friend> getAllFriendAndRecommend() {
        return friendMapper.getAllFriendAndRecommend();
    }

    @Override
    public List<Friend> getAllFriend() {
        return friendMapper.getAllFriend();
    }

    @Override
    public List<Friend> getAllRecommend() {
        return friendMapper.getAllRecommend();
    }

    @Override
    public Friend getFriendById(Long fId) {
        return friendMapper.getFriendById(fId);
    }

    @Override
    public Friend getRecommendById(Long fId) {
        return friendMapper.getRecommendById(fId);
    }

    @Override
    public Friend getFriendOrRecommendById(Long fId) {
        return friendMapper.getFriendOrRecommendById(fId);
    }

    @Override
    public boolean addFriendOrRecommend(Friend friend) {
        return friendMapper.addFriendOrRecommend(friend);
    }

    @Override
    public boolean deleteFriendOrRecommendById(Long fId) {
        return friendMapper.deleteFriendOrRecommendById(fId);
    }

    @Override
    public boolean deleteAllFriendAndRecommend() {
        return friendMapper.deleteAllFriendAndRecommend();
    }

    @Override
    public boolean deleteAllFriend() {
        return friendMapper.deleteAllFriend();
    }

    @Override
    public boolean deleteAllRecommend() {
        return friendMapper.deleteAllRecommend();
    }

    @Override
    public boolean updateFriendOrRecommend(Friend friend) {
        return friendMapper.updateFriendOrRecommend(friend);
    }
}
