package com.tao.demo.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.tao.demo.domain.entity.Permission;
import com.tao.demo.enums.PermissionTypeEnum;
import com.tao.demo.mapper.PermissionMapper;
import com.tao.demo.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

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
  public List<Permission> getInterfacePermissions() {
    return new LambdaQueryChainWrapper<>(baseMapper)
      .eq(Permission::getType, PermissionTypeEnum.INTERFACE)
      .list();
  }
}
