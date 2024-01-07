package com.tao.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @projectName: film
 * @package: cn.fdzc.edu.film.enums
 * @className: PermissionTypeEnum
 * @author: DemoTao
 * @description: TODO
 * @date: 2023/12/20 15:32
 * @version: 1.0
 */
@AllArgsConstructor
@Getter
public enum PermissionTypeEnum {
  /**
   * 菜单权限
   */
  MENU(1),
  /**
   * 接口权限
   */
  INTERFACE(2),
  ;
  
  /**
   * 用于数据库存储
   */
  @EnumValue
  private final Integer permissionType;
  
  
}
