package com.maple.server.exception;

import com.maple.common.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常
 *
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public R mySQLException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return R.error().message("该数据有关数据，操作失败！");
        }
        return R.error().message("数据库异常，操作失败！");
    }
}
