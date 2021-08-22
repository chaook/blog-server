package com.chao.blog.mapper;

import com.chao.blog.entity.Type;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TypeMapper {

    @Select("SELECT * FROM t_type WHERE type_id=#{typeId}")
    Type getTypeById(Long typeId);

    @Select("SELECT * FROM t_type")
    @Results({
            @Result(property = "typeId", column = "type_id", id = true),
            @Result(property = "blogNum", column = "type_id", one = @One(select = "com.chao.blog.mapper.BlogMapper.countBlogNumByTypeId"))
    })
    List<Type> getAllType();

    @Insert("INSERT INTO t_type (name) VALUES (#{name})")
    boolean addType(Type type);

    @Delete("DELETE FROM t_type WHERE type_id=#{typeId}")
    boolean deleteTypeById(Long typeId);

    @Delete("DELETE FROM t_type")
    boolean deleteAllType();

    @Update("<script>" +
            " UPDATE t_type" +
            "  <set>" +
            "   <if test='name!=null'>name=#{name}</if>" +
            "   <if test='createTime!=null'>create_time=#{createTime}</if>" +
            "   <if test='updateTime!=null'>update_time=#{updateTime}</if>" +
            "  </set>" +
            " WHERE type_id=#{typeId}" +
            "</script>")
    boolean updateType(Type type);
}
