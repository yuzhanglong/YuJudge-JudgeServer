package com.yzl.yujudge.controller.v1;

import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.configuration.AuthorizationConfiguration;
import com.yzl.yujudge.dto.LoginDTO;
import com.yzl.yujudge.dto.RegisterDTO;
import com.yzl.yujudge.service.UserService;
import com.yzl.yujudge.vo.AuthorizationVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    private final AuthorizationConfiguration authorizationConfiguration;

    public UserController(UserService userService, AuthorizationConfiguration authorizationConfiguration) {
        this.userService = userService;
        this.authorizationConfiguration = authorizationConfiguration;
    }

    /**
     * @author yuzhanglong
     * @description 注册一个用户
     * @date 2020-08-02 19:52:36
     */
    @PostMapping("/register")
    public UnifiedResponse register(@Validated @RequestBody RegisterDTO registerDTO) {
        userService.userRegister(registerDTO);
        return new UnifiedResponse("注册成功");
    }

    /**
     * @author yuzhanglong
     * @description 用户鉴权、登录
     * @date 2020-08-02 19:53:08
     */
    @PostMapping("/login")
    public UnifiedResponse login(@RequestBody @Validated LoginDTO loginDTO) {
        String token = userService.userLogin(loginDTO);
        AuthorizationVO authorizationVO = new AuthorizationVO();
        authorizationVO.setAccessToken(token);
        authorizationVO.setExpiresIn(authorizationConfiguration.getExpiredIn());
        return new UnifiedResponse(authorizationVO);
    }

    /**
     * @author yuzhanglong
     * @description 本接口仅用于token的测试
     * @date 2020-08-03 19:38:25
     */
    @GetMapping("/check_token")
    @AuthorizationRequired
    public UnifiedResponse checkToken() {
        Long userId = UserHolder.getUserId();
        System.out.println(userId);
        return new UnifiedResponse("验证成功,本接口仅用于token的测试");
    }


    /**
     * @author yuzhanglong
     * @description 下发验证码图片
     * @date 2020-08-03 19:51:21
     */
    @RequestMapping("/get_check_code")
    public UnifiedResponse getCheckCode() {
        Map<String, String> generatedCodeInfo = userService.generateCheckCode();
        return new UnifiedResponse(generatedCodeInfo);
    }
}
