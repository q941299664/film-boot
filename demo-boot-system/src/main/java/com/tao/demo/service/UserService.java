package com.tao.demo.service;

import com.github.yulichang.extension.mapping.base.MPJDeepService;
import com.tao.demo.domain.bo.UserRoleBo;
import com.tao.demo.domain.entity.User;
import com.tao.demo.domain.vo.UserInfoVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author LiTao
 * @since 2023-12-24
 */
public interface UserService extends MPJDeepService<User> {
  
  /**
   * 用户注册
   *
   * @param user 用户信息
   * @return 注册成功的用户信息
   */
  User register(User user);
  
  /**
   * 判断邮箱是否存在
   *
   * @param email 邮箱
   * @return true 存在，false 不存在
   */
  Boolean isEmailExists(String email);
  
  /**
   * 根据邮箱获取用户信息
   *
   * @param email 邮箱
   * @return 用户信息
   */
  User getByEmail(String email);
  
  /**
   * 设置登录用户缓存
   *
   * @param user 用户信息
   */
  void setLoginUserCache(User user);
  
  /**
   * 获取用户角色信息
   *
   * @return 用户角色信息
   */
  UserRoleBo getUserRole(Long userId);
  
  /**
   *
   * 获取当前登录用户
   * @return 用户信息
   */
  UserInfoVO getCurrLoginUser();
  
  
}
