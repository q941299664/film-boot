package com.tao.demo.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.domain.vo
 * @className: MenuMetaVO
 * @author: DemoTao
 * @description: TODO
 * @date: 2024/1/5 16:16
 * @version: 1.0
 */

@Data
public class MenuMetaVO {
  /**
   * 角色名称列表
   */
  private List<String> roles;
  /**
   * 需要登录才能访问
   */
  private Boolean requiresAuth;
  /**
   * 菜单图标
   */
  private String icon;
  /**
   * 侧边菜单和面包屑中展示的名字
   */
  private String locale;
  /**
   * 在菜单中隐藏
   */
  private Boolean hideInMenu;
  /**
   * 在子菜单中隐藏
   */
  private Boolean hideChildrenInMenu;
  /**
   * 当前激活的菜单。用于配置详情页时左侧激活的菜单路径
   */
  private String activeMenu;
  /**
   * 菜单排序
   */
  private Integer order;
  /**
   * 是否不缓存
   */
  private  Boolean noAffix;
  /**
   * 是否忽略缓存
   */
  private Boolean ignoreCache;
}
