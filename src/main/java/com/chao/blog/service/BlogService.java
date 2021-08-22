package com.chao.blog.service;

import com.chao.blog.entity.Blog;
import com.chao.blog.entity.TimeLine;
import com.chao.blog.exception.ImageException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlogById(Long blogId);

    List<Blog> getAllBlog();

    Map<String, List<TimeLine>> getTimeLine();

    PageInfo<Blog> getPublishedBlogByPage(Integer pageNum, Integer pageSize, Integer typeId);

    PageInfo<Blog> getPublishedBlogByPageAndTag(Integer pageNum, Integer pageSize, Integer tagId);

    Integer getPublishedBlogTotal();

    PageInfo<Blog> getBlogByPage(Integer pageNum, Integer pageSize);

    boolean addBlog(Blog blog);

    boolean deleteBlogById(Long blogId);

    boolean deleteAllBlog();

    boolean updateBlog(Blog blog);

    byte[] getBlogImage(String path) throws ImageException;

}
