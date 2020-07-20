package com.yzl.yujudge.core.exception.http;


/**
 * @author yuzhanglong
 */
public class NotFoundException extends HttpException {
    public NotFoundException(String code) {
        this.httpStatusCode = 404;
        this.code = code;
    }
}