package com.chao.blog.service.Impl;

import com.chao.blog.entity.Blog;
import com.chao.blog.entity.TimeLine;
import com.chao.blog.exception.ImageException;
import com.chao.blog.mapper.BlogMapper;
import com.chao.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    @Transactional
    public Blog getBlogById(Long blogId) {
        blogMapper.addViewsById(blogId);
        return blogMapper.getBlogById(blogId);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    public Map<String, List<TimeLine>> getTimeLine() {
        Map<String, List<TimeLine>> map = new LinkedHashMap<>();
        for (TimeLine tlBlog : blogMapper.getTimeLine()) {
            if (!map.containsKey(tlBlog.getMonth())) {
                map.put(tlBlog.getMonth(), new ArrayList<>());
            }
            String temMonth = tlBlog.getMonth();
            map.get(temMonth).add(tlBlog);
        }
        return map;
    }

    @Override
    public PageInfo<Blog> getPublishedBlogByPage(Integer pageNum, Integer pageSize, Integer typeId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> allBlog = blogMapper.getAllPublishedBlog(typeId);
        return new PageInfo<>(allBlog);
    }

    @Override
    public PageInfo<Blog> getPublishedBlogByPageAndTag(Integer pageNum, Integer pageSize, Integer tagId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> allBlog = blogMapper.getPublishedBlogByTag(tagId);
        return new PageInfo<>(allBlog);
    }

    @Override
    public Integer getPublishedBlogTotal() {
        return blogMapper.getPublishedBlogTotal();
    }

    @Override
    public PageInfo<Blog> getBlogByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> allBlog = blogMapper.getAllBlog();
        return new PageInfo<>(allBlog);
    }

    @Override
    public boolean addBlog(Blog blog) {
        if (blog.getViews()==null) {
            blog.setViews(0);
        }
        if (blog.getPublished()==null) {
            blog.setPublished(true);
        }
        return blogMapper.addBlog(blog);
    }

    @Override
    public boolean deleteBlogById(Long blogId) {
        return blogMapper.deleteBlogById(blogId);
    }

    @Override
    public boolean deleteAllBlog() {
        return blogMapper.deleteAllBlog();
    }

    @Override
    public boolean updateBlog(Blog blog) {
        return blogMapper.updateBlog(blog);
    }

    @Override
    public byte[] getBlogImage(String path) throws ImageException {
        byte[] image = new byte[0];
        try (FileInputStream imageReader = new FileInputStream(path);) {
            image = new byte[imageReader.available()];
            imageReader.read(image);
        } catch (FileNotFoundException e) {
            throw new ImageException("没有对应的图片");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
