package com.tao.demo.domain.vo;

import com.tao.demo.core.domain.BaseEntity;
import com.tao.demo.domain.entity.Permission;
import com.tao.demo.domain.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.domain.vo
 * @className: UserInfoVO
 * @author: DemoTao
 * @description: TODO
 * @date: 2024/1/2 14:49
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVO extends BaseEntity {
  /**
   * 用户名
   */
  private String username;
  /**
   * 邮箱
   */
  private String email;
  
  private List<Role> roles;
  
  private List<Permission> permissions;
}
