package com.maple.common;

/**
 * http 响应码
 */
public interface ResultCode {
    Integer SUCCESS = 200;//成功
    Integer ERROR = 99;//失败

    //token 101 -199
    Integer ERROR_TOKEN_INVALID = 101;
}
