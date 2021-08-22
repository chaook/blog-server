package com.chao.blog.mapper;

import com.chao.blog.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {

    @Select("SELECT * FROM t_tag WHERE tag_id=#{tagId}")
    Tag getTagById(Long tagId);

    @Select("SELECT * FROM t_tag")
    @Results({
            @Result(property = "tagId", column = "tag_id", id = true),
            @Result(property = "blogNum", column = "tag_id", one = @One(select = "com.chao.blog.mapper.BlogMapper.countBlogNumByTagId"))
    })
    List<Tag> getAllTag();

    @Select("SELECT * FROM t_tag AS t LEFT JOIN t_blog_tag AS b ON t.tag_id=b.tag_id " +
            "WHERE b.blog_id=#{blogId}")
    List<Tag> getTagByBlogId(Integer blogId);

    @Insert("INSERT INTO t_tag (name) VALUES (#name)")
    boolean addTag(Tag tag);

    @Delete("DELETE FROM t_tag WHERE gat_id=#{tagId}")
    boolean deleteTagById(Long tagId);

    @Delete("DELETE FROM t_tag")
    boolean deleteAllTag();

    @Update("<script>" +
            " UPDATE t_tag" +
            "  <set>" +
            "   <if test='name!=null'>name=#{name}</if>" +
            "   <if test='createTime!=null'>create_time=#{createTime}</if>" +
            "   <if test='updateTime!=null'>update_time=#{updateTime}</if>" +
            "  </set>" +
            " WHERE tag_id=#{tagId}" +
            "</script>")
    boolean updateTag(Tag tag);


}
