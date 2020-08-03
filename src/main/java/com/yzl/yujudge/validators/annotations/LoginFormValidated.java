package com.yzl.yujudge.validators.annotations;

import com.yzl.yujudge.validators.LoginFormValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author yuzhanglong
 * @description 登录表单验证
 * @date 2020-08-03 19:04:12
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = LoginFormValidator.class)
public @interface LoginFormValidated {
    String message() default "登录表单不合法，请重试";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}