package com.yzl.yujudge.service;

import com.github.dozermapper.core.Mapper;
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
        userGroup.setName(userGroupDTO.getName().toUpperCase());
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
    public void editUserGroup(Long userGroupId, UserGroupDTO userGroupDTO) {
        UserGroupEntity entity = userGroupRepository.findOneById(userGroupId);
        if (entity == null) {
            throw new NotFoundException("B0015");
        }
        entity.setDescription(userGroupDTO.getDescription());
        entity.setName(userGroupDTO.getName().toUpperCase());
        userGroupRepository.save(entity);
    }
}
