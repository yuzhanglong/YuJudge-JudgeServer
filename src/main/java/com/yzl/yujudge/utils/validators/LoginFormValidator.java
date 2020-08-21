package com.yzl.yujudge.utils.validators;

import com.yzl.yujudge.dto.LoginDTO;
import com.yzl.yujudge.utils.validators.annotations.LoginFormValidated;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author yuzhanglong
 * @description 登录表单验证
 * @date 2020-08-03 18:55:56
 */

public class LoginFormValidator implements ConstraintValidator<LoginFormValidated, LoginDTO> {

    @Override
    public boolean isValid(LoginDTO loginDTO, ConstraintValidatorContext constraintValidatorContext) {
        String nickname = loginDTO.getNickname();
        String password = loginDTO.getPassword();
        return isAtLeastHaveOneKeyAndNotNull(nickname, password);
    }

    /**
     * @param nickname 用户名
     * @param email    密码
     * @return 是否满足描述的条件，见 @description
     * @author yuzhanglong
     * @description 是否满足以下情况: 用户名和密码 不能为 "" 字符串 / 不得全为空，至少有一个满足需求
     * @date 2020-08-03 19:02:08
     */

    private Boolean isAtLeastHaveOneKeyAndNotNull(String nickname, String email) {
        // 不能为 "" 字符串
        if ("".equals(nickname) || "".equals(email)) {
            return false;
        }
        // 不得全为空，至少有一个满足需求
        return nickname != null || email != null;
    }
}
