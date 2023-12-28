package com.tao.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @projectName: film
 * @package: cn.fdzc.edu.film.enums
 * @className: PermissionTypeEnum
 * @author: DemoTao
 * @description: TODO
 * @date: 2023/12/20 15:32
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum PermissionTypeEnum {
  /**
   * 菜单权限
   */
  MENU("1", "menu"),
  INTERFACE("2", "interface"),
  ;
  
  @EnumValue
  private String value;
  @JsonValue
  private String label;
}
