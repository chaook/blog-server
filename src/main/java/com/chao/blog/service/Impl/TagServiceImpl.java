package com.chao.blog.service.Impl;

import com.chao.blog.entity.Tag;
import com.chao.blog.mapper.TagMapper;
import com.chao.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public Tag getTagById(Long tagId) {
        return tagMapper.getTagById(tagId);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    @Override
    public boolean addTag(Tag tag) {
        return tagMapper.addTag(tag);
    }

    @Override
    public boolean deleteTagById(Long tagId) {
        return tagMapper.deleteTagById(tagId);
    }

    @Override
    public boolean deleteAllTag() {
        return tagMapper.deleteAllTag();
    }

    @Override
    public boolean updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }
}
