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
  SYS_TABLES("系统模块", "demo-boot-system", "sys", "sys_user_tb", "sys_role_tb", "sys_permission_tb", "sys_user_role_tb", "sys_role_permission_tb", "sys_dict_tb", "sys_dict_data_tb"),
  FILM_TABLES("电影模块", "film-movie", "film","film_movie_tb", "film_cinema_tb", "film_hall_tb", "film_showtime_tb", "film_comment_tb", "film_order_tb"),
  
  ;
  
  /**
   * 表名列表
   */
  private Collection<String> tables;
  /**
   * 名称
   */
  private String name;
  
  /**
   * 模块名
   */
  private String modelName;
  
  /**
   * 前缀
   */
  private String prefix;
  
  TableTypeEnum(String name, String modelName, String prefix, String... tables) {
    this.name = name;
    this.modelName = modelName;
    this.prefix = prefix;
    this.tables = Set.of(tables);
  }
}
