package com.tao.demo.service;

import com.github.yulichang.extension.mapping.base.MPJDeepService;
import com.tao.demo.domain.bo.RolePermissionBo;
import com.tao.demo.domain.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
public interface RoleService extends MPJDeepService<Role> {
  
  /**
   * 获取角色权限信息
   *
   * @param roleId 角色id
   * @return 角色权限信息
   */
  RolePermissionBo getRolePermission(Long roleId);
  
  /**
   * 获取默认角色
   *
   * @return 默认角色列表
   */
  List<Role> getDefaultRoles();
}
