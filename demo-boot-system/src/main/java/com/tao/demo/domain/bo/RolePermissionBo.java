package com.tao.demo.domain.bo;

import com.tao.demo.domain.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.domain.bo
 * @className: UserRoleBo
 * @author: DemoTao
 * @description: 用户服务层实体
 * @date: 2023/12/25 22:52
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePermissionBo {
  /**
   * 角色ID
   */
  private Long roleId;
  /**
   * 角色名
   */
  private String name;
  
  /**
   * 角色权限列表
   */
  private List<Permission> permissions;
}
