package com.yzl.yujudge.service;

import com.yzl.yujudge.core.configuration.AuthorizationConfiguration;
import com.yzl.yujudge.core.exception.http.ForbiddenException;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.LoginDTO;
import com.yzl.yujudge.dto.RegisterDTO;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.repository.UserRepository;
import com.yzl.yujudge.store.redis.RedisOperations;
import com.yzl.yujudge.utils.CheckCodeUtil;
import com.yzl.yujudge.utils.SecurityUtil;
import com.yzl.yujudge.utils.ToEntityUtil;
import com.yzl.yujudge.utils.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * @author yuzhanglong
 * @description 与用户相关的业务逻辑
 * @date 2020-08-03 13:25:11
 */

@Service
public class UserService {
    public static final long CHECK_CODE_EXPIRED_TIME_IN_SECOND = 60 * 10;

    private final UserRepository userRepository;
    private final AuthorizationConfiguration authorizationConfiguration;
    private final RedisOperations redisOperations;

    public UserService(UserRepository userRepository, AuthorizationConfiguration authorizationConfiguration, RedisOperations redisOperations) {
        this.userRepository = userRepository;
        this.authorizationConfiguration = authorizationConfiguration;
        this.redisOperations = redisOperations;
    }

    /**
     * @param loginDTO 登录信息的数据传输对象
     * @author yuzhanglong
     * @description 用户登录
     * 在这里，我们会将用户名和密码进行比对，
     * 如果登录成功，我们会返回一个token字符串
     * 接下来调用相关接口只需要带上token即可
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
     * @param registerDTO 注册信息的数据传输对象
     * @author yuzhanglong
     * @description 用户注册
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
     * @author yuzhanglong
     * @description 通过userId，生成新的token给客户端
     * 接下来客户端需要调用相关接口只需要传入token
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
     * @author yuzhanglong
     * @description 生成验证码信息，以供返回给前端
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
     * @param key     验证码对应的key
     * @param content 客户端传入的验证码内容
     * @author yuzhanglong
     * @description 检测验证码是否通过
     * @date 2020-08-03 21:22:00
     */
    private Boolean isCheckCodePass(String key, String content) {
        String value = (String) redisOperations.get(key);
        if (value == null) {
            return false;
        }
        return value.equals(content);
    }
}
