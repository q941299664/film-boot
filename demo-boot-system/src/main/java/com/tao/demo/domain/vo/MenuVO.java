package com.tao.demo.domain.vo;

import com.tao.demo.core.domain.BaseEntity;
import com.tao.demo.domain.entity.Permission;
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
public class MenuVO extends BaseEntity {
  /**
   * 用户名
   */
  private String username;
  
  
  private List<Permission> permissions;
}
