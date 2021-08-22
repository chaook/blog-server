package com.chao.blog.controller;

import com.chao.blog.entity.Blog;
import com.chao.blog.entity.ResultCode;
import com.chao.blog.entity.ResultInfo;
import com.chao.blog.exception.ImageException;
import com.chao.blog.service.BlogService;
import com.chao.blog.utils.ResultUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private Environment env;

    @Autowired
    BlogService blogService;

    @GetMapping
    public ResultInfo<List<Blog>> getAllBlog() {
        List<Blog> allBlog = blogService.getAllBlog();
        return ResultUtil.build(ResultCode.SUCCESS, allBlog);
    }

    @GetMapping("/{pageNum:\\d+}/{pageSize:\\d+}")
    public ResultInfo<PageInfo<Blog>> getPublishedBlogByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize,
    @RequestParam(value = "type", required = false) Integer typeId, @RequestParam(value = "tag", required = false) Integer tagId) {
        PageInfo<Blog> blogsInfo;
        if (tagId == null) {
            blogsInfo = blogService.getPublishedBlogByPage(pageNum, pageSize, typeId);
        } else {
            blogsInfo = blogService.getPublishedBlogByPageAndTag(pageNum, pageSize, tagId);
        }
        /*Map<String, Object> map = new HashMap<>();
        Set<Object> page = new HashSet<>();
        page.add(blogsInfo.getTotal());
        page.add(blogsInfo.getPageNum());
        page.add(blogsInfo.isHasNextPage());
        page.add(blogsInfo.isHasPreviousPage());
        page.add(blogsInfo.isIsFirstPage());
        page.add(blogsInfo.isIsLastPage());
        map.put("page", page);*/
        return ResultUtil.build(ResultCode.SUCCESS, blogsInfo);
    }

    @RequestMapping("/{blogId:\\d+}")
    public ResultInfo<Blog> getBlogById(@PathVariable Long blogId) {
        Blog blog = blogService.getBlogById(blogId);
        return ResultUtil.build(ResultCode.SUCCESS, blog);
    }

    @GetMapping(value = "/image/{imageName}")
    public byte[] getImage(@PathVariable String imageName) throws ImageException, IOException {
        String imagePath = env.getProperty("blog.image.location");
        if (imagePath ==null) {
            throw new ImageException("未配置图片根路径，请配置 'blog.image.location' 属性");
        }
        return blogService.getBlogImage(imagePath + File.separator + imageName);
    }

    @GetMapping("/total")
    public ResultInfo<Integer> getPublishedBlogTotal() {
        Integer total = blogService.getPublishedBlogTotal();
        return ResultUtil.build(ResultCode.SUCCESS, total);
    }

    @PostMapping
    public ResultInfo<Void> addBlog(@RequestBody Blog blog) {
        boolean b = blogService.addBlog(blog);
        return ResultUtil.build(b ? ResultCode.SUCCESS : ResultCode.INNER_ERROR);
    }
}
