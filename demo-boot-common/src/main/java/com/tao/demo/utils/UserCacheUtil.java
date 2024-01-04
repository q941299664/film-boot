package com.tao.demo.utils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.utils
 * @className: UserCacheUtil
 * @author: DemoTao
 * @description: 定义用户权限列表、角色列表缓存工具类
 * @date: 2023/12/25 22:41
 * @version: 1.0
 */
@Component
public class UserCacheUtil {
  
  @Resource
  RedisUtil redisUtilTmp;
  
  private static RedisUtil redisUtil;
  
  @PostConstruct
  public void init() {
    redisUtil = redisUtilTmp;
  }
  
  /**
   * 用户权限列表缓存前缀
   */
  public static final String USER_PERMISSION_CACHE_PREFIX = "user:permission:";
  /**
   * 用户角色列表缓存前缀
   */
  public static final String USER_ROLE_CACHE_PREFIX = "user:role:";
  
  /**
   * 获取用户权限列表缓存key
   *
   * @param userId 用户id
   * @return 用户权限列表缓存key
   */
  public static String getUserPermissionCacheKey(Long userId) {
    return USER_PERMISSION_CACHE_PREFIX + userId;
  }
  
  /**
   * 获取用户角色列表缓存key
   *
   * @param userId 用户id
   * @return 用户角色列表缓存key
   */
  public static String getUserRoleCacheKey(Long userId) {
    return USER_ROLE_CACHE_PREFIX + userId;
  }
  
  /**
   * 设置用户权限列表缓存
   *
   * @param userId      用户id
   * @param permissions 权限列表
   */
  public static void setUserPermissionCache(Long userId, String permissions) {
    List<Object> userPermissionCache = getUserPermissionCache(userId);
    AtomicReference<Boolean> isSet = new AtomicReference<>(false);
    userPermissionCache.forEach(up->{
      String sUp = (String) up;
      if(sUp.equals(permissions)){
        isSet.set(true);
      }
    });
    if(!isSet.get()){
      // 设置用户角色列表缓存
      redisUtil.lSet(getUserPermissionCacheKey(userId), permissions);
    }
    
  }
  
  
  /**
   * 获取用户权限列表缓存
   *
   * @param userId 用户id
   * @return 用户权限列表缓存
   */
  public static List<Object> getUserPermissionCache(Long userId) {
    // 获取用户权限列表缓存
    return redisUtil.lGet(getUserPermissionCacheKey(userId), 0, -1);
  }
  
  /**
   * 设置用户角色列表缓存
   *
   * @param userId 用户id
   * @param roles  角色列表
   */
  public static void setUserRoleCache(Long userId, String roles) {
    List<Object> userRoleCache = getUserRoleCache(userId);
    AtomicReference<Boolean> isSet = new AtomicReference<>(false);
    userRoleCache.forEach(up->{
      String sUp = (String) up;
      if(sUp.equals(roles)){
        isSet.set(true);
      }
    });
    if(!isSet.get()){
      // 设置用户权限列表缓存
      redisUtil.lSet(getUserRoleCacheKey(userId), roles);
    }
    
  }
  
  /**
   * 获取用户角色列表缓存
   *
   * @param userId 用户id
   * @return 用户角色列表缓存
   */
  public static List<Object> getUserRoleCache(Long userId) {
    // 获取用户角色列表缓存
    return redisUtil.lGet(getUserRoleCacheKey(userId), 0, -1);
  }
  
}
