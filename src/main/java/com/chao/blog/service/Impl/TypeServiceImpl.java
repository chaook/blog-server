package com.chao.blog.service.Impl;

import com.chao.blog.entity.Type;
import com.chao.blog.mapper.TypeMapper;
import com.chao.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeMapper typeMapper;

    @Override
    public Type getTypeById(Long typeId) {
        return typeMapper.getTypeById(typeId);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    public boolean addType(Type type) {
        return typeMapper.addType(type);
    }

    @Override
    public boolean deleteTypeById(Long typeId) {
        return typeMapper.deleteTypeById(typeId);
    }

    @Override
    public boolean deleteAllType() {
        return typeMapper.deleteAllType();
    }

    @Override
    public boolean updateType(Type type) {
        return typeMapper.updateType(type);
    }
}
