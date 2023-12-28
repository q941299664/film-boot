package com.tao.demo.config.shiro.realm;


import com.tao.demo.utils.JwtUtil;
import com.tao.demo.utils.UserCacheUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: zlgl
 * @description: 自定义Realm
 * @author: LiTao
 * @create:
 **/
@Log4j2
public class CustomRealm extends AuthorizingRealm {
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof BearerToken;
  }
  
  
  /**
   * @Description: 授权配置
   * @Param:
   * @return:
   * @Author: LiTao
   * @Date:
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    // 取到用户id
    String token = principals.toString();
    Map<String, Object> payLoad = JwtUtil.getPayLoad(token);
    Long userId = (Long) payLoad.get("id");
    // 1. 获取角色列表
    List<?> roleCache = UserCacheUtil.getUserRoleCache(userId);
    Set<String> roleNameList = roleCache.stream().map(o -> (String) o).collect(Collectors.toSet());
    log.info("当前用户拥有角色：" + roleNameList);
    // 2. 添加角色
    simpleAuthorizationInfo.setRoles(roleNameList);
    // 3.获取权限列表
    List<?> permissionCache = UserCacheUtil.getUserPermissionCache(userId);
    Set<String> permissionNames = permissionCache.stream().map(o -> (String) o).collect(Collectors.toSet());
    // 4. 添加权限
    log.info("当前用户拥有权限：" + permissionNames);
    simpleAuthorizationInfo.setStringPermissions(permissionNames);
    return simpleAuthorizationInfo;
  }
  
  protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
    return SecurityUtils.getSubject().getSession().getId();
  }
  
  /**
   * @Description: 认证配置
   * @Param:
   * @return:
   * @Author: LiTao
   * @Date:
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    log.info("Shiro认证");
    BearerToken bearerToken = (BearerToken) authenticationToken;
    String token = bearerToken.getToken();
    Map<String, Object> payLoad = JwtUtil.getPayLoad(token);
    Integer id = (Integer) payLoad.get("id");
    log.info("token: {}, 当前用户id：{}", token, id);
    // 判断账号是否存在
    if (id == null) {
      // 返回null -> shiro就会知道这是用户不存在的异常
      return null;
    }
    Long userId = Long.parseLong(String.valueOf(id));
    // 判断账号是否有效
    if (!JwtUtil.parseJwt(token)) {
      return null;
    }
    // 匹配密码,并将userId存入信息中
    return new SimpleAccount(userId, token, getName());
  }
}

