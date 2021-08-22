package com.chao.blog.utils;

import com.chao.blog.entity.ResultCode;
import com.chao.blog.entity.ResultInfo;

public class ResultUtil {

    static public <T> ResultInfo<T> build(ResultCode resultCode) {
        return build(resultCode, null);
    }

    static public <T> ResultInfo<T> build(ResultCode resultCode, T data) {
        return new ResultInfo<>(resultCode.getCode(), resultCode.getMsg(), data);
    }
}
