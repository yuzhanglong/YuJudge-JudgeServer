package com.yzl.yujudge.controller.v1;

import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.configuration.AuthorizationConfiguration;
import com.yzl.yujudge.core.enumeration.PermissionEnum;
import com.yzl.yujudge.dto.LoginDTO;
import com.yzl.yujudge.dto.RegisterDTO;
import com.yzl.yujudge.dto.UserUserGroupDTO;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.service.UserService;
import com.yzl.yujudge.utils.EntityToVoListMapper;
import com.yzl.yujudge.utils.EntityToVoMapper;
import com.yzl.yujudge.vo.AuthorizationVO;
import com.yzl.yujudge.vo.PaginationVO;
import com.yzl.yujudge.vo.UserInfoBasicVO;
import com.yzl.yujudge.vo.UserInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 用户相关接口控制层
 *
 * @author yuzhanglong
 * @date 2020-08-02 19:50:22
 */

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthorizationConfiguration authorizationConfiguration;

    public UserController(UserService userService, AuthorizationConfiguration authorizationConfiguration) {
        this.userService = userService;
        this.authorizationConfiguration = authorizationConfiguration;
    }

    /**
     * 注册一个用户
     *
     * @author yuzhanglong
     * @date 2020-08-02 19:52:36
     */
    @PostMapping("/register")
    public UnifiedResponse register(@Validated @RequestBody RegisterDTO registerDTO) {
        userService.userRegister(registerDTO);
        return new UnifiedResponse("注册成功");
    }

    /**
     * 用户鉴权、登录
     *
     * @param loginDTO 登录数据传输对象
     * @author yuzhanglong
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
     * 本接口仅用于token的测试
     *
     * @author yuzhanglong
     * @date 2020-08-03 19:38:25
     */
    @GetMapping("/check_token")
    @AuthorizationRequired
    public UnifiedResponse checkToken() {
        Long userId = UserHolder.getUserId();
        return new UnifiedResponse("验证成功,本接口仅用于token的测试");
    }


    /**
     * 下发验证码图片
     *
     * @author yuzhanglong
     * @date 2020-08-03 19:51:21
     */
    @RequestMapping("/get_check_code")
    public UnifiedResponse getCheckCode() {
        Map<String, String> generatedCodeInfo = userService.generateCheckCode();
        return new UnifiedResponse(generatedCodeInfo);
    }

    /**
     * 获取近期活跃用户
     *
     * @author yuzhanglong
     * @description 需要获取的用户数目
     * @date 2020-08-07 20:13:09
     */
    @GetMapping("/get_active_user")
    @AuthorizationRequired
    public UnifiedResponse getActiveUser(@RequestParam @NotNull Integer amount) {
        List<UserEntity> userEntities = userService.getActiveUsers(amount);
        EntityToVoListMapper<UserEntity, UserInfoBasicVO> mapper = new EntityToVoListMapper<>(userEntities, UserInfoBasicVO.class);
        List<UserInfoBasicVO> userInfoVOList = mapper.getItems();
        return new UnifiedResponse(userInfoVOList);
    }


    /**
     * 通过用户id，获取用户信息
     * 可以传入用户id，如果没有传入，则我们返回调用者的个人信息
     *
     * @author yuzhanglong
     * @date 2020-08-08 13:11:24
     */
    @GetMapping("/user_info")
    @AuthorizationRequired
    public UnifiedResponse getUserInfo(@RequestParam(defaultValue = "") Long uid) {
        if (uid == null) {
            uid = UserHolder.getUserId();
        }
        UserEntity user = userService.getUserInfo(uid);
        EntityToVoMapper<UserEntity, UserInfoVO> mapper = new EntityToVoMapper<>(user, UserInfoVO.class);
        return new UnifiedResponse(mapper.getViewObject());
    }

    /**
     * 分页获取所有用户的基本信息
     *
     * @param count 单页数量
     * @param start 页码
     * @author yuzhanglong
     * @date 2020-08-16 13:02:20
     */
    @GetMapping("/get_users")
    @AuthorizationRequired(permission = PermissionEnum.ADMIN)
    public UnifiedResponse getUsers(
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count,
            @RequestParam(defaultValue = "") Long group) {
        Page<UserEntity> userEntities = userService.getUsers(start, count, group);
        PaginationVO<UserEntity, UserInfoVO> paginationVO = new PaginationVO<>(userEntities, UserInfoVO.class);
        return new UnifiedResponse(paginationVO);
    }


    /**
     * 删除用户
     *
     * @param userId 需要删除的用户Id
     * @author yuzhanglong
     * @date 2020-8-22 14:05:20
     */
    @DeleteMapping("/delete_user/{userId}")
    @AuthorizationRequired(permission = PermissionEnum.ADMIN)
    public UnifiedResponse deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new UnifiedResponse("删除用户成功");
    }

    /**
     * 创建用户
     *
     * @param user 用户信息
     * @author yuzhanglong
     * @date 2020-8-22 14:05:20
     */
    @PostMapping("/user")
    @AuthorizationRequired(permission = PermissionEnum.ADMIN)
    public UnifiedResponse createUser(@RequestBody LoginDTO user) {
        userService.createUser(user.getNickname(), user.getPassword());
        return new UnifiedResponse("创建用户成功~");
    }

    /**
     * 为用户分配用户组
     *
     * @author yuzhanglong
     * @date 2020-9-6 21:30:37
     */
    @PutMapping("/user_user_groups")
    @AuthorizationRequired(permission = PermissionEnum.ADMIN)
    public UnifiedResponse addUserToUserGroup(@RequestBody @Validated UserUserGroupDTO userUserGroupDTO) {
        userService.allocateUserUserGroups(userUserGroupDTO.getUserId(), userUserGroupDTO.getUserGroupIds());
        return new UnifiedResponse("添加成功");
    }
}