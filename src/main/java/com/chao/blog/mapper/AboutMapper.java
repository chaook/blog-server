package com.chao.blog.mapper;

import com.chao.blog.entity.About;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AboutMapper {

    @Select("SELECT COUNT(*) FROM t_about")
    Integer getAboutTotal();

    @Select("SELECT * FROM t_about")
    About getAbout();

    @Delete("DELETE FROM t_about")
    boolean deleteAbout();

    @Update("<script>" +
            " UPDATE t_about" +
            "  <set>" +
            "   <if test='image!=null'>image=#{image}</if>" +
            "   <if test='qq!=null'>qq=#{qq}</if>" +
            "   <if test='wechat!=null'>wechat=#{wechat}</if>" +
            "   <if test='aboutMe!=null'>about_me=#{aboutMe}</if>" +
            "   <if test='record!=null'>record=#{record}</if>" +
            "   <if test='createTime!=null'>create_time=#{createTime}</if>" +
            "   <if test='updateTime!=null'>update_time=#{updateTime}</if>" +
            "  </set>" +
            "</script>")
    boolean updateAbout(About about);

    @Insert("INSERT INTO t_about (image, qq, wechat, about_me, record)" +
            " VALUES (#{image}, #{qq}, #{wechat}, #{aboutMe}, #{record})")
    boolean addAbout(About about);
}
