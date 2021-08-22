package com.chao.blog.service;

import com.chao.blog.entity.Tag;

import java.util.List;

public interface TagService {

    Tag getTagById(Long tagId);

    List<Tag> getAllTag();

    boolean addTag(Tag tag);

    boolean deleteTagById(Long tagId);

    boolean deleteAllTag();

    boolean updateTag(Tag tag);

}
