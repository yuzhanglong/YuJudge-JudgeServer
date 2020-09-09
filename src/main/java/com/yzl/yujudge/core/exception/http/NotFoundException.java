package com.yzl.yujudge.core.exception.http;


/**
 * NotFound错误
 *
 * @author yuzhanglong
 * @date 2020-7-20 16:54:33
 */
public class NotFoundException extends HttpException {
    public NotFoundException(String code) {
        this.httpStatusCode = 404;
        this.code = code;
    }
}