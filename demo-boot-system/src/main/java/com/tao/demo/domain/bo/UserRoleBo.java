package com.tao.demo.domain.bo;

import com.tao.demo.domain.entity.Role;
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
public class UserRoleBo {
  /**
   * 用户ID
   */
  private Long userId;
  /**
   * 用户名
   */
  private String username;
  
  /**
   * 用户角色列表
   */
  private List<Role> roles;
}
