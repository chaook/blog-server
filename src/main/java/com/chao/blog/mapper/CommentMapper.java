package com.chao.blog.mapper;

import com.chao.blog.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("SELECT * FROM t_comment WHERE co_id=#{coId}")
    Comment getCommentById(Long coId);

    @Results(id = "commentMap", value = {
            @Result(property = "coId", column = "co_id", id = true),
//            @Result(property = "childComments", column = "co_id", many = @Many(select = "com.chao.blog.mapper.CommentMapper.getChildComment")),
            @Result(property = "parentComment", column = "parent_id", one = @One(select = "com.chao.blog.mapper.CommentMapper.getCommentById"))
    })
    @Select("SELECT * FROM t_comment")
    List<Comment> getAllComment();

//    @Results({
//            @Result(property = "coId", column = "co_id", id = true),
//            @Result(property = "parentComment", column = "parent_id", one = @One(select = "com.chao.blog.mapper.CommentMapper.getCommentById"))
//    })
    @ResultMap({"commentMap"})
    @Select("SELECT * FROM t_comment WHERE parent_id=#{coId}")
    List<Comment> getChildComment(Long coId);

    @Select("SELECT COUNT(*) FROM t_comment")
    Integer getCommentTotal();

    @ResultMap({"commentMap"})
    @Select("SELECT * FROM t_comment WHERE blog_id=#{blogId} AND parent_id IS NULL")
    List<Comment> getCommentByBlogId(Long blogId);

    @Select("SELECT * FROM t_comment WHERE parent_id=#{parentID}")
    List<Comment> getCommentByParentId(Long parentId);

    @Insert("INSERT INTO t_comment (name, email, content, blog_id, parent_id, is_deleted)" +
            "VALUES (#{name}, #{email}, #{content}, #{blogId}, #{parentId}, #{isDeleted})")
    boolean addComment(Comment comment);

    @Delete("DELETE FROM t_comment WHERE co_id=#{coId}")
    boolean deleteCommentById(Long coId);

    @Delete("DELETE FROM t_comment")
    boolean deleteAllComment();

    @Update("<script>" +
            " UPDATE t_comment" +
            "  <set>" +
            "   <if test='name!=null'>name=#{name}</if>" +
            "   <if test='email!=null'>email=#{email}</if>" +
            "   <if test='content!=null'>content=#{content}</if>" +
            "   <if test='blogId!=null'>blog_id=#{blogId}</if>" +
            "   <if test='parentId!=null'>parent_id=#{parentId}</if>" +
            "   <if test='isDeleted!=null'>is_deleted=#{isDeleted}</if>" +
            "  </set>" +
            " WHERE co_id=#{coId}" +
            "</script>")
    boolean updateComment(Comment comment);

}
