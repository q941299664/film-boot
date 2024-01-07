package com.tao.demo.service;

import com.github.yulichang.extension.mapping.base.MPJDeepService;
import com.tao.demo.domain.entity.Permission;
import com.tao.demo.enums.PermissionTypeEnum;

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
   * 获取指定类型数据
   *
   * @return 指定类型数据
   */
  List<Permission> getPermissionsByType(PermissionTypeEnum type);
  
  /**
   * 获取用户指定类型数据
   *
   * @return 指定类型数据
   */
  List<Permission> getPermissionsByTypeAndUserId(PermissionTypeEnum type, Long userId);
  
  
  
}
