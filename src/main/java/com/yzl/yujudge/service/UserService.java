package com.yzl.yujudge.service;

import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.configuration.AuthorizationConfiguration;
import com.yzl.yujudge.core.enumeration.BaseUserGroupEnum;
import com.yzl.yujudge.core.exception.http.ForbiddenException;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.LoginDTO;
import com.yzl.yujudge.dto.RegisterDTO;
import com.yzl.yujudge.model.PermissionEntity;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.model.UserGroupEntity;
import com.yzl.yujudge.repository.UserRepository;
import com.yzl.yujudge.store.redis.RedisOperations;
import com.yzl.yujudge.utils.CheckCodeUtil;
import com.yzl.yujudge.utils.SecurityUtil;
import com.yzl.yujudge.utils.ToEntityUtil;
import com.yzl.yujudge.utils.TokenUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 与用户相关的业务逻辑
 *
 * @author yuzhanglong
 * @date 2020-08-03 13:25:11
 */

@Service
public class UserService {
    private static final long CHECK_CODE_EXPIRED_TIME_IN_SECOND = 60 * 10;
    private static final int GET_RECENT_USER_MAX_SIZE = 10;

    private final UserRepository userRepository;
    private final AuthorizationConfiguration authorizationConfiguration;
    private final RedisOperations redisOperations;

    public UserService(UserRepository userRepository, AuthorizationConfiguration authorizationConfiguration, RedisOperations redisOperations) {
        this.userRepository = userRepository;
        this.authorizationConfiguration = authorizationConfiguration;
        this.redisOperations = redisOperations;
    }

    /**
     * 用户登录
     * 在这里，我们会将用户名和密码进行比对，
     * 如果登录成功，我们会返回一个token字符串
     * 接下来调用相关接口只需要带上token即可
     *
     * @param loginDTO 登录信息的数据传输对象
     * @author yuzhanglong
     * @date 2020-08-03 13:30:29
     */
    public String userLogin(LoginDTO loginDTO) {
        // TODO: 支持邮箱登录
        UserEntity user = userRepository.findByNickname(loginDTO.getNickname());
        if (user == null) {
            throw new NotFoundException("B0006");
        }
        // 验证密码正误
        String passwordHash = user.getPassword();
        String passwordToCheck = loginDTO.getPassword();
        boolean isPasswordPass = SecurityUtil.checkPasswordHash(passwordToCheck, passwordHash);
        String key = loginDTO.getCheckCodeKey();
        boolean isCodePass = isCheckCodePass(key, loginDTO.getCheckCodeContent());
        // 移除本次验证码的相关信息
        redisOperations.remove(key);
        if (!isCodePass) {
            // 验证码异常
            throw new ForbiddenException("B0009");
        }
        if (!isPasswordPass) {
            // 用户密码异常
            throw new ForbiddenException("B0007");
        }
        return generateUserTokenByUserId(user.getId());
    }

    /**
     * 用户注册
     *
     * @param registerDTO 注册信息的数据传输对象
     * @author yuzhanglong
     * @date 2020-08-03 18:42:30
     */
    public void userRegister(RegisterDTO registerDTO) {
        String key = registerDTO.getCheckCodeKey();
        boolean isCodePass = isCheckCodePass(key, registerDTO.getCheckCodeContent());
        // 移除本次验证码的相关信息
        redisOperations.remove(key);
        if (!isCodePass) {
            // 验证码异常
            throw new ForbiddenException("B0009");
        }
        String nickname = registerDTO.getNickname();
        // 判断用户是否已经存在
        if (userRepository.findByNickname(nickname) != null) {
            throw new ForbiddenException("B0008");
        }
        UserEntity userEntity = ToEntityUtil.registerDtoToUserEntity(registerDTO);
        // 将密码hash，并存入entity对象
        userEntity.setPassword(SecurityUtil.generatePasswordHash(registerDTO.getPassword()));
        userRepository.save(userEntity);
    }


    /**
     * 通过userId，生成新的token给客户端
     * 接下来客户端需要调用相关接口只需要传入token
     *
     * @author yuzhanglong
     * @date 2020-08-03 16:30:20
     */
    private String generateUserTokenByUserId(Long userId) throws NullPointerException {
        if (userId == null) {
            throw new NullPointerException("userId不存在");
        }
        String salt = authorizationConfiguration.getSecretKey();
        Integer expiredIn = authorizationConfiguration.getExpiredIn();
        return TokenUtil.generateAuthToken(userId.toString(), salt, expiredIn);
    }


    /**
     * 生成验证码信息，以供返回给前端
     *
     * @author yuzhanglong
     * @date 2020-08-03 20:15:29
     */
    public Map<String, String> generateCheckCode() {
        Map<String, String> codeInfo = CheckCodeUtil.getCheckCode();
        String content = codeInfo.get(CheckCodeUtil.CODE_CONTENT_KEY_NAME);
        String key = UUID.randomUUID().toString();
        Boolean isSet = redisOperations.set(key, content, CHECK_CODE_EXPIRED_TIME_IN_SECOND);
        // TODO: 对isSet进行处理
        // 移除codeContent, 添加生成的key
        codeInfo.remove(CheckCodeUtil.CODE_CONTENT_KEY_NAME);
        codeInfo.replace(CheckCodeUtil.CODE_KEY_KEY_NAME, key);
        return codeInfo;
    }

    /**
     * 检测验证码是否通过
     *
     * @param key     验证码对应的key
     * @param content 客户端传入的验证码内容
     * @author yuzhanglong
     * @date 2020-08-03 21:22:00
     */
    private Boolean isCheckCodePass(String key, String content) {
        String value = (String) redisOperations.get(key);
        if (value == null) {
            return false;
        }
        return value.equals(content);
    }

    /**
     * 获取近期活跃用户信息
     *
     * @param userAmount 用户的数量
     * @author yuzhanglong
     * @date 2020-08-07 16:18:31
     */
    public List<UserEntity> getActiveUser(Integer userAmount) {
        int finalSize = userAmount > GET_RECENT_USER_MAX_SIZE ? GET_RECENT_USER_MAX_SIZE : userAmount;
        Pageable pageable = PageRequest.of(0, finalSize);
        return userRepository.findByOrderBySubmissionAmountDesc(pageable);
    }

    /**
     * @param userId 用户id
     * @author yuzhanglong
     * @description 获取近期活跃用户信息
     * @date 2020-08-08 13:01:16
     */
    public UserEntity getUserInfo(Long userId) {
        UserEntity userEntity = userRepository.findOneById(userId);
        if (userEntity == null) {
            throw new NotFoundException("B0010");
        }
        return userEntity;
    }

    /**
     * 获取用户信息分页对象
     *
     * @param count 单页数量
     * @param start 页码
     * @author yuzhanglong
     * @date 2020-08-16 13:53:33
     */
    public Page<UserEntity> getUsers(Integer start, Integer count) {
        Pageable pageable = PageRequest.of(start, count);
        // 当没有传入权限时，直接查找所有用户
        return userRepository.findAll(pageable);
    }

    /**
     * 获取用户信息分页对象
     *
     * @param userId 用户ID
     * @author yuzhanglong
     * @date 2020-8-22 13:41:07
     */
    public void deleteUser(Long userId) {
        UserEntity user = userRepository.findOneById(userId);
        // 用户为空
        if (user == null) {
            throw new NotFoundException("B0006");
        }
        // 可能出现是自己的情况
        if (UserHolder.getUserId().equals(userId)) {
            throw new NotFoundException("B0006");
        }
        userRepository.softDeleteById(userId);
    }

    /**
     * 根据用户id判断是否为管理员用户
     *
     * @param userId 用户ID
     * @author yuzhanglong
     * @date 2020-8-22 13:41:07
     */
    public Boolean isRootUser(Long userId) {
        UserEntity userEntity = userRepository.findOneById(userId);
        // 管理员用户只有一个用户组，即ROOT
        UserGroupEntity userGroupEntity = userEntity.getUserGroups().get(0);
        return userGroupEntity.getName().equals(BaseUserGroupEnum.COMMON.name());
    }

    /**
     * 判断某个permission是否符合某个用户
     * 检测用户是否有资格访问资源
     * 在数据表层面，我们可以理解为：
     * 用户【user】对应的用户组【user_group】是否拥有这个权限【permission】，三张表全为多对多关系
     *
     * @param userId 用户ID
     * @author yuzhanglong
     * @date 2020-8-22 13:41:07
     */
    public Boolean isUserPermissionAccepted(Long userId, String permission) {
        // TODO: 权限数据一般很少改变，但调用较为频繁，采用缓存，避免大量查询
        UserEntity userEntity = userRepository.findOneById(userId);
        List<UserGroupEntity> userGroupEntityList = userEntity.getUserGroups();
        for (UserGroupEntity userGroupEntity : userGroupEntityList) {
            List<PermissionEntity> permissionEntityList = userGroupEntity.getPermissions();
            for (PermissionEntity permissionEntity : permissionEntityList) {
                String permissionName = permissionEntity.getName();
                if (permission.equals(permissionName)) {
                    return true;
                }
            }
        }
        return false;
    }
}