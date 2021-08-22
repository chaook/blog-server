package com.chao.blog.entity;

import java.util.Date;

public class Friend {
    private Long fId;
    private String name;
    private String webSite;
    private String description;
    private Boolean isRem;     //是否为推荐链接，如果为 true，则是推荐链接，如果为 false，则为友情链接
    private Date createTime;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRem() {
        return isRem;
    }

    public void setRem(Boolean rem) {
        isRem = rem;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
