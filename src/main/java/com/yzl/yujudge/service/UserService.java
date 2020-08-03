package com.yzl.yujudge.service;

import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.configuration.AuthorizationConfiguration;
import com.yzl.yujudge.core.exception.http.ForbiddenException;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.LoginDTO;
import com.yzl.yujudge.dto.RegisterDTO;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.repository.UserRepository;
import com.yzl.yujudge.utils.SecurityUtil;
import com.yzl.yujudge.utils.ToEntityUtil;
import com.yzl.yujudge.utils.TokenUtil;
import org.springframework.stereotype.Service;

/**
 * @author yuzhanglong
 * @description 与用户相关的业务逻辑
 * @date 2020-08-03 13:25:11
 */

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AuthorizationConfiguration authorizationConfiguration;

    public UserService(UserRepository userRepository, AuthorizationConfiguration authorizationConfiguration) {
        this.userRepository = userRepository;
        this.authorizationConfiguration = authorizationConfiguration;
    }

    /**
     * @author yuzhanglong
     * @description 用户登录
     * 在这里，我们会将用户名和密码进行比对，
     * 如果登录成功，我们会返回一个token字符串
     * 接下来调用相关接口只需要带上token即可
     * @date 2020-08-03 13:30:29
     */

    public String userLogin(LoginDTO loginDTO) {
        UserEntity user = userRepository.findByNickname(loginDTO.getNickname());
        if (user == null) {
            throw new NotFoundException("B0006");
        }
        // 验证密码正误
        String passwordHash = user.getPassword();
        String passwordToCheck = loginDTO.getPassword();
        boolean isPass = SecurityUtil.checkPasswordHash(passwordToCheck, passwordHash);
        if (isPass) {
            return generateUserTokenByUserId(user.getId());
        } else {
            throw new ForbiddenException("B0007");
        }
    }

    /**
     * @param registerDTO 注册信息的数据传输对象
     * @author yuzhanglong
     * @description 用户注册
     * @date 2020-08-03 18:42:30
     */

    public void userRegister(RegisterDTO registerDTO) {
        //TODO: 验证码的判断
        String nickname = registerDTO.getNickname();
        String email = registerDTO.getEmail();
        // 判断用户是否已经存在
        if (isUserExisted(nickname)) {
            throw new ForbiddenException("B0008");
        }
        // TODO: 以注解的形式分离此处的验证操作，另外还有字符串为 "" 的情况，都是不允许的
        if (nickname == null && email == null) {
            // 允许使用用户名或者密码登录，如果两者都没有传入，我们拒绝之
            throw new NotFoundException("A0002");
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
     * @param userName 用户名
     * @author yuzhanglong
     * @description 判断某个用户名对应的用户是否存在
     * @date 2020-08-03 16:40:18
     */

    private Boolean isUserExisted(String userName) {
        UserEntity user = userRepository.findByNickname(userName);
        return user != null;
    }
}
