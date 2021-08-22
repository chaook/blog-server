package com.chao.blog.mapper;

import com.chao.blog.entity.Friend;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("SELECT * FROM t_friend")
    List<Friend> getAllFriendAndRecommend();

    @Select("SELECT * FROM t_friend WHERE is_rem=0")
    List<Friend> getAllFriend();

    @Select("SELECT * FROM t_friend WHERE is_rem=1")
    List<Friend> getAllRecommend();

    @Select("SELECT * FROM t_friend WHERE is_rem=0 AND f_id=#{fId}")
    Friend getFriendById(Long fId);

    @Select("SELECT * FROM t_friend WHERE is_rem=1 AND f_id=#{fId}")
    Friend getRecommendById(Long fId);

    @Select("SELECT * FROM t_friend WHERE f_id=#{fId}")
    Friend getFriendOrRecommendById(Long fId);

    @Insert("INSERT INTO t_friend (name, web_site, description, is_rem)" +
            "VALUES (#{name}, #{webSite}, #{description}, #{isRem})")
    boolean addFriendOrRecommend(Friend friend);

    @Delete("DELETE FROM t_friend WHERE f_id=#{fId}")
    boolean deleteFriendOrRecommendById(Long fId);

    @Delete("DELETE FROM t_friend")
    boolean deleteAllFriendAndRecommend();

    @Delete("DELETE FROM t_friend WHERE is_rem=0")
    boolean deleteAllFriend();

    @Delete("DELETE FROM t_friend WHERE is_rem=1")
    boolean deleteAllRecommend();

    @Update("<script>" +
            " UPDATE t_friend" +
            "  <set>" +
            "   <if test='name!=null'>name=#{name}</if>" +
            "   <if test='webSite!=null'>web_site=#{webSite}</if>" +
            "   <if test='description!=null'>description=#{description}</if>" +
            "   <if test='isRem!=null'>is_rem=#{isRem}</if>" +
            "  </set>" +
            " WHERE f_id=#{fId}" +
            "</script>")
    boolean updateFriendOrRecommend(Friend friend);
}
