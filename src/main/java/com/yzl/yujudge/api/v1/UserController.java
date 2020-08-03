package com.yzl.yujudge.api.v1;

import com.wf.captcha.utils.CaptchaUtil;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.dto.LoginDTO;
import com.yzl.yujudge.dto.RegisterDTO;
import com.yzl.yujudge.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/**
 * @author yuzhanglong
 * @description 用户相关接口控制层
 * @date 2020-08-02 19:50:22
 */

@RestController
@Validated
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @author yuzhanglong
     * @description 注册一个用户
     * @date 2020-08-02 19:52:36
     */
    @PostMapping("/register")
    private UnifiedResponse register(@RequestBody @NotNull RegisterDTO registerDTO) {
        userService.userRegister(registerDTO);
        return new UnifiedResponse();
    }

    /**
     * @author yuzhanglong
     * @description 用户鉴权、登录
     * @date 2020-08-02 19:53:08
     */
    @PostMapping("/login")
    public UnifiedResponse login(@RequestBody @NotNull LoginDTO loginDTO) {
        String data = userService.userLogin(loginDTO);
        return new UnifiedResponse(data);
    }


    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.out(request, response);
    }
}
