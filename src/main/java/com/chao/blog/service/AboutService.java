package com.chao.blog.service;

import com.chao.blog.entity.About;

public interface AboutService {

    Integer getAboutTotal();

    About getAbout();

    boolean deleteAbout();

    boolean updateAbout(About about);

    boolean addAbout(About about);
}
