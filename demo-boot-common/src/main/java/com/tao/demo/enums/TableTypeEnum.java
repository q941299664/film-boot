package com.tao.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.enums
 * @className: TableTypeEnum
 * @author: DemoTao
 * @description: 每个模块对应需要生成的表
 * @date: 2023/12/23 17:41
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum TableTypeEnum {
  /**
   * 系统模块对应数据表
   */
  SYS_TABLES("系统模块", "sys_user_tb", "sys_role_tb", "sys_permission_tb", "sys_user_role_tb", "sys_role_permission_tb", "sys_dict_tb", "sys_dict_data_tb"),
  ;
  
  /**
   * 表名列表
   */
  private Collection<String> tables;
  /**
   * 模块名称
   */
  private String name;
  
  TableTypeEnum(String name, String... tables) {
    this.name = name;
    this.tables = Set.of(tables);
  }
}
