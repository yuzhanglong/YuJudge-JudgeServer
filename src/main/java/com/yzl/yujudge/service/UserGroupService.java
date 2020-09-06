package com.yzl.yujudge.service;

import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.enumeration.BaseUserGroupEnum;
import com.yzl.yujudge.core.exception.http.ForbiddenException;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.UserGroupDTO;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.model.UserGroupEntity;
import com.yzl.yujudge.repository.UserGroupRepository;
import com.yzl.yujudge.repository.UserRepository;
import com.yzl.yujudge.utils.EntityToVoListMapper;
import com.yzl.yujudge.vo.UserGroupVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 与用户组相关的业务逻辑
 * @date 2020-8-22 14:18:43
 */

@Service
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public UserGroupService(
            UserGroupRepository userGroupRepository,
            UserRepository userRepository,
            Mapper mapper) {
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    /**
     * 创建用户组
     * 注意：用户组的实际名称（对应name字段）将被改成全大写风格
     *
     * @param userGroupDTO 用户组相关数据传输对象
     * @author yuzhanglong
     * @date 2020-8-22 14:27:22
     */
    public void createUserGroup(UserGroupDTO userGroupDTO) {
        UserGroupEntity userGroup = mapper.map(userGroupDTO, UserGroupEntity.class);
        String name = userGroupDTO.getName().toUpperCase();
        if (isDuplicateUserGroupName(name)) {
            throw new ForbiddenException("B0017");
        }
        validateUserGroupName(name);
        userGroup.setName(name);
        userGroupRepository.save(userGroup);
    }

    /**
     * 分页查找用户组
     *
     * @return 用户组相关视图层对象列表
     * @author yuzhanglong
     * @date 2020-8-22 14:41:51
     */
    public List<UserGroupVO> getUserGroups() {
        List<UserGroupEntity> entities = userGroupRepository.findAll();
        EntityToVoListMapper<UserGroupEntity, UserGroupVO> entityToVoListMapper = new EntityToVoListMapper<>(entities, UserGroupVO.class);
        return entityToVoListMapper.getItems();
    }

    /**
     * 删除用户组
     *
     * @author yuzhanglong
     * @date 2020-8-22 14:58:30
     */
    public void deleteUserGroup(Long userGroupId) {
        UserGroupEntity userGroupEntityToDelete = getUserGroupById(userGroupId);
        // 默认分组禁止删除
        validateUserGroupName(userGroupEntityToDelete.getName());
        userGroupRepository.delete(userGroupEntityToDelete);
    }

    /**
     * 编辑用户组
     * 注意：用户组的实际名称（对应name字段）将被改成全大写风格
     *
     * @param userGroupId  被编辑的用户组id
     * @param userGroupDTO 用户组的数据传输对象
     * @author yuzhanglong
     * @date 2020-8-22 14:58:30
     */
    public void setUserGroup(Long userGroupId, UserGroupDTO userGroupDTO) {
        UserGroupEntity entity = getUserGroupById(userGroupId);
        // 不允许的名称
        validateUserGroupName(entity.getName());
        String nameToEdit = userGroupDTO.getName().toUpperCase();
        validateUserGroupName(nameToEdit);
        entity.setDescription(userGroupDTO.getDescription());
        entity.setName(nameToEdit);
        userGroupRepository.save(entity);
    }


    /**
     * 验证用户组的合法性
     * 项目预先安排了一些用户组,
     * 例如超级管理员、一般用户等，
     * 这些用户组如果被删除可能会影响部分逻辑的正常进行
     *
     * @param name 用户组名称
     * @author yuzhanglong
     * @date 2020-8-22 18:29:26
     */
    public void validateUserGroupName(String name) {
        boolean isDefaultUserGroup = BaseUserGroupEnum.isDefaultUserGroup(name);
        if (isDefaultUserGroup) {
            throw new NotFoundException("B0016");
        }
    }

    /**
     * 查询用户名称是否重复，我们不允许重复的用户组名称出现
     *
     * @param name 用户组名称
     * @return 是否重复
     * @author yuzhanglong
     * @date 2020-8-22 18:29:26
     */
    private Boolean isDuplicateUserGroupName(String name) {
        UserGroupEntity userGroupEntity = userGroupRepository.findOneByName(name);
        return userGroupEntity != null;
    }

    /**
     * 添加一个或者多个用户到用户组
     *
     * @param uidList         用户列表
     * @param userGroupEntity 用户组实体
     * @author yuzhanglong
     * @date 2020-9-6 18:17:00
     */
    public void addUsersInUserGroup(List<Long> uidList, UserGroupEntity userGroupEntity) {
        for (Long uid : uidList) {
            UserEntity user = userRepository.findOneById(uid);
            if (user == null) {
                throw new NotFoundException("B0006");
            }
            userGroupEntity.getUsers().add(user);
        }
        userGroupRepository.save(userGroupEntity);
    }

    /**
     * 根据名称获取用户组
     *
     * @param name 用户组名称
     * @author yuzhanglong
     * @date 2020-9-6 18:17:00
     */
    public UserGroupEntity getUserGroupByName(String name) {
        UserGroupEntity userGroupEntity = userGroupRepository.findOneByName(name);
        if (userGroupEntity == null) {
            throw new NotFoundException("B0015");
        }
        return userGroupEntity;
    }

    /**
     * 根据id获取用户组
     *
     * @param userGroupId 用户组ID
     * @author yuzhanglong
     * @date 2020-9-6 21:20:19
     */
    public UserGroupEntity getUserGroupById(Long userGroupId) {
        UserGroupEntity entity = userGroupRepository.findOneById(userGroupId);
        if (entity == null) {
            throw new NotFoundException("B0015");
        }
        return entity;
    }

    /**
     * 判断用户是否在用户组中
     *
     * @param userId          用户id
     * @param userGroupEntity 用户组实体
     * @author yuzhanglong
     * @date 2020-9-6 23:02:17
     */
    public Boolean isUserInUserGroup(Long userId, UserGroupEntity userGroupEntity) {
        for (UserEntity user : userGroupEntity.getUsers()) {
            if (user.getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断用户题目集操作无限制
     *
     * @author yuzhanglong
     * @date 2020-9-6 23:47:56
     */
    public Boolean isUserProblemSetFree() {
        List<UserGroupEntity> g = UserHolder.getUserUserGroups();
        for (UserGroupEntity userGroupEntity : g) {
            boolean isRoot = userGroupEntity.getName().equals(BaseUserGroupEnum.ROOT.name());
            boolean isProblemManager = userGroupEntity.getName().equals(BaseUserGroupEnum.PROBLEM_MANAGER.name());
            if (isRoot || isProblemManager) {
                return true;
            }
        }
        return false;
    }
}