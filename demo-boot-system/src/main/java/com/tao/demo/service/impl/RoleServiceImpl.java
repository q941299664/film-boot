package com.tao.demo.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.tao.demo.domain.bo.RolePermissionBo;
import com.tao.demo.domain.entity.Permission;
import com.tao.demo.domain.entity.Role;
import com.tao.demo.domain.entity.RolePermission;
import com.tao.demo.mapper.RoleMapper;
import com.tao.demo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Service
public class RoleServiceImpl extends MPJBaseServiceImpl<RoleMapper, Role> implements RoleService {
  
  @Override
  public RolePermissionBo getRolePermission(Long roleId) {
    return baseMapper.selectJoinOne(RolePermissionBo.class,
      new MPJLambdaWrapper<Role>()
        .selectAll(Role.class)
        .eq(Role::getId, roleId)
        .innerJoin(RolePermission.class, RolePermission::getRoleId, Role::getId)
        .innerJoin(Permission.class, Permission::getId, RolePermission::getPermissionId)
        .selectCollection(Permission.class, RolePermissionBo::getPermissions)
    );
  }
  
  @Override
  public List<Role> getDefaultRoles() {
    return new LambdaQueryChainWrapper<>(baseMapper)
      .eq(Role::getIsDefault, true)
      .list();
  }
}
