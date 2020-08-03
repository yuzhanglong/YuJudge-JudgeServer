package com.yzl.yujudge.validators.annotations;

import com.yzl.yujudge.validators.LanguageListAcceptedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author yuzhanglong
 * @description 语言类型验证
 * @date 2020-6-22 15:44
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = LanguageListAcceptedValidator.class)
public @interface LanguageListAccepted {
    String message() default "编程语言不合法，请重试";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}