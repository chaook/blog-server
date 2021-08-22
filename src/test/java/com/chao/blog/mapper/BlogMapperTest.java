package com.chao.blog.mapper;

import com.chao.blog.entity.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogMapperTest {
    @Autowired
    BlogMapper blogMapper;

    @Test
    public void testSelectById(){
        Blog blog = blogMapper.getBlogById(2L);
        System.out.println(blog);
    }

    @Test
    public void testAddBlog() {
        Blog blog = new Blog();
        blog.setTitle("Another Test Title");
        blog.setContent("Another Test Content");
        blog.setAuthor("Another Test Author");
        blog.setImage("Another Test Image");
        blog.setPublished(false);
        blog.setViews(0);
        blogMapper.addBlog(blog);
    }

    /*@Test
    public void testSelectAllPublishedBlog() {
        List<Blog> allPublishedBlog = blogMapper.getAllPublishedBlog();
        System.out.println(allPublishedBlog.size());
        System.out.println(allPublishedBlog);
    }*/

    @Test
    public void testSelectAllBlog() {
        List<Blog> allBlog = blogMapper.getAllBlog();
        System.out.println(allBlog.size());
        System.out.println(allBlog);
    }
}
