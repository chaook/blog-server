package com.chao.blog.service.Impl;

import com.chao.blog.entity.About;
import com.chao.blog.mapper.AboutMapper;
import com.chao.blog.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutServiceImpl implements AboutService {

    @Autowired
    AboutMapper aboutMapper;

    @Override
    public Integer getAboutTotal() {
        return aboutMapper.getAboutTotal();
    }

    @Override
    public About getAbout() {
        Integer total = aboutMapper.getAboutTotal();
        if (total > 1) {
            //发生错误
            return null;
        }
        return aboutMapper.getAbout();
    }

    @Override
    public boolean deleteAbout() {
        return aboutMapper.deleteAbout();
    }

    @Override
    public boolean updateAbout(About about) {
        return aboutMapper.updateAbout(about);
    }

    @Override
    public boolean addAbout(About about) {
        return aboutMapper.addAbout(about);
    }
}
