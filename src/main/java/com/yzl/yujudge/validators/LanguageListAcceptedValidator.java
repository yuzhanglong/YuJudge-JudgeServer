package com.yzl.yujudge.validators;

import com.yzl.yujudge.core.enumeration.LanguageEnum;
import com.yzl.yujudge.dto.ProblemLimitationDTO;
import com.yzl.yujudge.validators.annotations.LanguageListAccepted;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 语言类型验证
 * @date 2020-7-29 14:46:51
 */
public class LanguageListAcceptedValidator implements ConstraintValidator<LanguageListAccepted, ProblemLimitationDTO> {
    @Override
    public boolean isValid(ProblemLimitationDTO limitationDTO, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }

    /**
     * @author yuzhanglong
     * @description 是否有重复的字段
     * @date 2020-7-26 19:02:03
     */
    private Boolean isLanguageRepeat(List<String> languages) {
        HashSet<String> hashSet = new HashSet<>(languages);
        return hashSet.size() == languages.size();
    }

    /**
     * @author yuzhanglong
     * @description 是否在支持的语言范围内
     * @date 2020-7-26 19:02:48
     */
    private Boolean isInAcceptLanguage(List<String> languages) {
        for (String s : languages) {
            if (!LanguageEnum.isLanguageAccepted(s)) {
                return false;
            }
        }
        return true;
    }
}
