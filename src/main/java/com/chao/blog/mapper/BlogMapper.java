package com.chao.blog.mapper;

import com.chao.blog.entity.Blog;
import com.chao.blog.entity.TimeLine;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface BlogMapper {

    @Select("SELECT * FROM t_blog WHERE blog_id=#{blogId}")
    @ResultMap({"blogResultMap"})
    Blog getBlogById(Long blogId);

    @Select("SELECT * FROM t_blog")
    List<Blog> getAllBlog();

    @Select("<script>" +
            " SELECT * FROM t_blog " +
            "  <where>" +
            "   is_published=1" +
            "   <if test='typeId!=null'> AND type_id=#{typeId} </if>" +
            "  </where>" +
            "</script>")
    @Results(id = "blogResultMap", value =  {
            @Result(property = "blogId", column = "blog_id", id = true),
            @Result(property = "tags", column = "blog_id", many = @Many(select = "com.chao.blog.mapper.TagMapper.getTagByBlogId")),
            @Result(property = "type", column = "type_id", one = @One(select = "com.chao.blog.mapper.TypeMapper.getTypeById")),
    })
    List<Blog> getAllPublishedBlog(Integer typeId);

    @Select("SELECT * FROM t_blog AS b LEFT JOIN t_blog_tag AS t ON b.blog_id=t.blog_id " +
            "WHERE t.tag_id=#{tagId}")
    @ResultMap({"blogResultMap"})
    List<Blog> getPublishedBlogByTag(Integer tagId);

    @Select("SELECT COUNT(*) FROM t_blog WHERE is_published=1")
    Integer getPublishedBlogTotal();

    @Insert("INSERT INTO t_blog " +
            "(title, content, outline, author, image, is_published, type_id, views)" +
            "VALUES (#{title}, #{content}, #{outline}, #{author}, #{image}, #{isPublished}, #{typeId}, #{views})")
    boolean addBlog(Blog blog);

    @Delete("DELETE FROM t_blog WHERE blog_id=#{blogId}")
    boolean deleteBlogById(Long blogId);

    @Delete("DELETE FROM t_blog")
    boolean deleteAllBlog();

    @Update("<script> UPDATE t_blog" +
        " <set>" +
        "  <if test='title!=null'>title=#{title},</if>" +
        "  <if test='content!=null'>content=#{content},</if>" +
        "  <if test='outline!=null'>outline=#{outline},</if>" +
        "  <if test='author!=null'>author=#{author},</if>" +
        "  <if test='image!=null'>title=#{image},</if>" +
        "  <if test='isPublished!=null'>is_published=#{isPublished},</if>" +
        "  <if test='views!=null'>views=#{views},</if>" +
        "  <if test='createTime!=null'>create_time=#{createTime},</if>" +
        "  <if test='updateTime!=null'>update_time=#{updateTime},</if>" +
        " </set>" +
        "WHERE blog_id=#{blogId}" +
        "</script>")
    boolean updateBlog(Blog blog);

    @Update("UPDATE t_blog SET views=views+1 WHERE blog_id=#{blogId}")
    boolean addViewsById(Long blogId);

    /*
        时间线
     */
    @Select("SELECT DATE_FORMAT(create_time,'%c-%d') AS 'date',DATE_FORMAT(create_time,'%Y.%m') AS 'month', title, blog_id " +
            "FROM t_blog WHERE is_published=1 " +
            "GROUP BY blog_id, DATE_FORMAT(create_time,'%Y-%m') ORDER BY create_time desc")
    List<TimeLine> getTimeLine();

    //----------------------------------
//    @Select("SELECT COUNT(blog_id) FROM t_blog_tag " +
//            "WHERE tag_id=#{tagId}")
    @Select("SELECT COUNT(*) FROM t_blog AS b JOIN t_blog_tag AS t ON b.blog_id=t.blog_id " +
            "WHERE b.is_published=1 AND t.tag_id=#{tagId}")
    Integer countBlogNumByTagId(Long tagId);

    @Select("SELECT COUNT(*) FROM t_blog WHERE is_published=1 AND type_id=#{typeId}")
    Integer countBlogNumByTypeId(Long typeId);
}
