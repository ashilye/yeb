package com.maple.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一公共返回对象
 */
@Data
public class R {

    @ApiModelProperty(value = "返回码 200：成功, 其他失败")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    //私有的构造器
    private R() {}

    //成功的静态方法
    public static R success() {
        R r = new R();
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    //失败的静态方法
    public static R error() {
        R r = new R();
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
