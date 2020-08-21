package com.yzl.yujudge.utils.validators.annotations;

import com.yzl.yujudge.utils.validators.LanguageAcceptedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author yuzhanglong
 * @description 语言类型验证（多个）
 * @date 2020-7-29 14:41:50
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = LanguageAcceptedValidator.class)
public @interface LanguageAccepted {
    String message() default "编程语言不合法，请重试";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}