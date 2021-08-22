package com.chao.blog.service;

import com.chao.blog.entity.Friend;

import java.util.List;

public interface FriendService {

    List<Friend> getAllFriendAndRecommend();

    List<Friend> getAllFriend();

    List<Friend> getAllRecommend();

    Friend getFriendById(Long fId);

    Friend getRecommendById(Long fId);

    Friend getFriendOrRecommendById(Long fId);

    boolean addFriendOrRecommend(Friend friend);

    boolean deleteFriendOrRecommendById(Long fId);

    boolean deleteAllFriendAndRecommend();

    boolean deleteAllFriend();

    boolean deleteAllRecommend();

    boolean updateFriendOrRecommend(Friend friend);

}
