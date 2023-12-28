package com.tao.demo.utils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

/**
 * @author DemoTao
 */
@Component
public class ShiroUtil {
  @Resource
  RedisSessionDAO redisSessionDAO;
  private static RedisSessionDAO sessionDAO;
  
  /**
   * 私有构造器
   **/
  @PostConstruct
  private void init() {
    sessionDAO = redisSessionDAO;
  }
  
  /**
   * 获取当前用户Session
   *
   * @Return 用户信息
   */
  public static Session getSession() {
    return SecurityUtils.getSubject().getSession();
  }
  
  /**
   * 用户登出
   */
  public static void logout() {
    SecurityUtils.getSubject().logout();
  }
  
  /**
   * 获取当前用户信息
   *
   * @Return SysUserEntity 用户信息
   */
  public static Long getCurrLoginUserId() {
    Object principal = SecurityUtils.getSubject().getPrincipal();
    return Objects.nonNull(principal) ? (Long) principal : null;
  }
  
  /**
   * 删除用户缓存信息
   *
   * @param userId          用户Id
   * @param isRemoveSession 是否删除Session，删除后用户需重新登录
   */
  public static void deleteCache(Long userId, boolean isRemoveSession) {
    //从缓存中获取Session
    Session session = null;
    // 获取当前已登录的用户session列表
    Collection<Session> sessions = sessionDAO.getActiveSessions();
    Long sessionUserId;
    Object attribute = null;
    // 遍历Session,找到该用户id对应的Session
    for (Session sessionInfo : sessions) {
      attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
      if (attribute == null) {
        continue;
      }
      sessionUserId = (Long) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
      if (sessionUserId == null) {
        continue;
      }
      if (Objects.equals(sessionUserId, userId)) {
        session = sessionInfo;
        // 清除该用户以前登录时保存的session，强制退出  -> 单用户登录处理
        if (isRemoveSession) {
          sessionDAO.delete(session);
        }
      }
    }
    
    if (session == null || attribute == null) {
      return;
    }
    //删除session
    if (isRemoveSession) {
      sessionDAO.delete(session);
    }
    //删除Cache，再访问受限接口时会重新授权
    DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
    Authenticator authc = securityManager.getAuthenticator();
    ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
  }
  
  /**
   * 批量删除用户缓存信息
   *
   * @Param userId  用户名称
   * @Param isRemoveSession 是否删除Session，删除后用户需重新登录
   */
  public static void deleteCache(Collection<Long> userId, boolean isRemoveSession) {
    //从缓存中获取Session
    Session session = null;
    // 获取当前已登录的用户session列表
    Collection<Session> sessions = sessionDAO.getActiveSessions();
    Long sessionUserId;
    Object attribute = null;
    // 遍历Session,找到该用户id对应的Session
    for (Session sessionInfo : sessions) {
      attribute = sessionInfo.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
      if (attribute == null) {
        continue;
      }
      sessionUserId = (Long) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
      if (sessionUserId == null) {
        continue;
      }
      if (userId.contains(sessionUserId)) {
        session = sessionInfo;
        // 清除该用户以前登录时保存的session，强制退出  -> 单用户登录处理
        if (isRemoveSession) {
          sessionDAO.delete(session);
        }
      }
    }
    
    if (session == null || attribute == null) {
      return;
    }
    //删除session
    if (isRemoveSession) {
      sessionDAO.delete(session);
    }
    //删除Cache，再访问受限接口时会重新授权
    DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
    Authenticator authc = securityManager.getAuthenticator();
    ((LogoutAware) authc).onLogout((SimplePrincipalCollection) attribute);
  }
  
  /**
   * 从缓存中获取指定用户ID的Session
   *
   * @param userId 用户id
   */
  private static Session getSessionByUserId(Long userId) {
    // 获取当前已登录的用户session列表
    Collection<Session> sessions = sessionDAO.getActiveSessions();
    Long sessionUserId;
    Object attribute;
    // 遍历Session,找到该用户名称对应的Session
    for (Session session : sessions) {
      attribute = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
      if (attribute == null) {
        continue;
      }
      sessionUserId = (Long) ((SimplePrincipalCollection) attribute).getPrimaryPrincipal();
      if (sessionUserId == null) {
        continue;
      }
      if (Objects.equals(sessionUserId, userId)) {
        return session;
      }
    }
    return null;
  }
  
}
