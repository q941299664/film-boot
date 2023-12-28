package com.tao.demo.service;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.LinkedHashMap;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.service
 * @className: ShiroService
 * @author: DemoTao
 * @description: TODO
 * @date: 2023/12/23 18:47
 * @version: 1.0
 */
public interface ShiroService {
  /**
   * 初始化权限 -> 拿全部权限
   *
   * @param :
   * @return: java.util.Map<java.lang.String, java.lang.String>
   */
  LinkedHashMap<String, String> loadFilterChainDefinitionMap();
  
  /**
   * 在对uri权限进行增删改操作时，需要调用此方法进行动态刷新加载数据库中的uri权限
   *
   * @param shiroFilterFactoryBean
   * @param roleId
   * @param isRemoveSession:
   * @return void
   */
  void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, Long roleId, Boolean isRemoveSession);
  
  /**
   * shiro动态权限加载 -> 原理：删除shiro缓存，重新执行doGetAuthorizationInfo方法授权角色和权限
   *
   * @param roleId
   * @param isRemoveSession:
   * @return: void
   */
  void updatePermissionByRoleId(Long roleId, Boolean isRemoveSession);
}
