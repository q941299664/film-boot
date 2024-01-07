package com.tao.demo.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.tao.demo.domain.entity.Permission;
import com.tao.demo.domain.entity.RolePermission;
import com.tao.demo.domain.entity.UserRole;
import com.tao.demo.enums.PermissionTypeEnum;
import com.tao.demo.mapper.PermissionMapper;
import com.tao.demo.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Service
public class PermissionServiceImpl extends MPJBaseServiceImpl<PermissionMapper, Permission> implements PermissionService {
  
  @Override
  public List<Permission> getPermissionsByType(PermissionTypeEnum type){
    return getPermissionsByTypeAndUserId(type, null);
  }
  
  @Override
  public List<Permission> getPermissionsByTypeAndUserId(PermissionTypeEnum type, Long userId){
    MPJLambdaWrapper<Permission> permissionMPJLambdaWrapper = new MPJLambdaWrapper<Permission>()
      .selectAll(Permission.class)
      .eq(Permission::getPermissionType, type.getPermissionType())
      .leftJoin(RolePermission.class, RolePermission::getPermissionId, Permission::getId)
      .leftJoin(UserRole.class, UserRole::getRoleId, RolePermission::getRoleId)
      .eq(Objects.nonNull(userId), UserRole::getUserId, userId)
      .orderByDesc(Permission::getSort);
    return listDeep(permissionMPJLambdaWrapper);
  }
}
