package com.chao.blog.service;

import com.chao.blog.entity.Type;

import java.util.List;

public interface TypeService {

    Type getTypeById(Long typeId);

    List<Type> getAllType();

    boolean addType(Type type);

    boolean deleteTypeById(Long typeId);

    boolean deleteAllType();

    boolean updateType(Type type);
}
