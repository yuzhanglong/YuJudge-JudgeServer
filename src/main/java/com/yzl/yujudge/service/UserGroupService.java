package com.yzl.yujudge.service;

import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.core.enumeration.BaseUserGroupEnum;
import com.yzl.yujudge.core.exception.http.ForbiddenException;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.UserGroupDTO;
import com.yzl.yujudge.model.UserGroupEntity;
import com.yzl.yujudge.repository.UserGroupRepository;
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
    private final Mapper mapper;

    public UserGroupService(UserGroupRepository userGroupRepository, Mapper mapper) {
        this.userGroupRepository = userGroupRepository;
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
        UserGroupEntity userGroupEntityToDelete = userGroupRepository.findOneById(userGroupId);
        if (userGroupEntityToDelete == null) {
            throw new NotFoundException("B0015");
        }
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
        UserGroupEntity entity = userGroupRepository.findOneById(userGroupId);
        if (entity == null) {
            throw new NotFoundException("B0015");
        }
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
}
