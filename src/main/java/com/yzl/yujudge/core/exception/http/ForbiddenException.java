package com.yzl.yujudge.core.exception.http;

/**
 * @author yuzhanglong
 */
public class ForbiddenException extends HttpException {
    public ForbiddenException(String code) {
        this.code = code;
        this.httpStatusCode = 403;
    }
}
