package com.yzl.yujudge.validators;

import com.yzl.yujudge.core.enumeration.LanguageEnum;
import com.yzl.yujudge.dto.SubmissionDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author yuzhanglong
 * @description 语言类型验证
 * @date 2020-7-29 14:46:38
 */
public class LanguageAcceptedValidator implements ConstraintValidator<LanguageAccepted, SubmissionDTO> {
    @Override
    public boolean isValid(SubmissionDTO submissionDTO, ConstraintValidatorContext constraintValidatorContext) {
        String language = submissionDTO.getLanguage();
        return isInAcceptLanguage(language);
    }

    /**
     * @author yuzhanglong
     * @description 是否在支持的语言范围内
     * @date 2020-7-29 14:47:06
     */
    private Boolean isInAcceptLanguage(String language) {
        return LanguageEnum.isLanguageAccepted(language);
    }
}