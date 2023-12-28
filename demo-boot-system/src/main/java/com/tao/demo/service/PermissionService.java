package com.tao.demo.service;

import com.github.yulichang.extension.mapping.base.MPJDeepService;
import com.tao.demo.domain.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
public interface PermissionService extends MPJDeepService<Permission> {
  /**
   * 获取接口权限
   *
   * @return 接口权限菜单
   */
  List<Permission> getInterfacePermissions();
}
