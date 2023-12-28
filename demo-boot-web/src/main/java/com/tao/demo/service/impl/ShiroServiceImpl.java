package com.tao.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.tao.demo.domain.entity.Permission;
import com.tao.demo.exception.GlobalException;
import com.tao.demo.service.PermissionService;
import com.tao.demo.service.ShiroService;
import com.tao.demo.service.UserRoleService;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.service.impl
 * @className: ShiroServiceImpl
 * @author: DemoTao
 * @description: TODO
 * @date: 2023/12/23 18:46
 * @version: 1.0
 */
@Log4j2
@Service
public class ShiroServiceImpl implements ShiroService {
  
  @Resource
  private PermissionService permissionService;
  
  @Resource
  private UserRoleService userRoleService;
  
  @Override
  public LinkedHashMap<String, String> loadFilterChainDefinitionMap() {
    // 权限控制map
    LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
    // 配置过滤:不会被拦截的链接 -> 放行 start ----------------------------------------------------------
    // 放行接口
    filterChainDefinitionMap.put("/user/login", "anon");
    filterChainDefinitionMap.put("/user/register", "anon");
    filterChainDefinitionMap.put("/user/otherLogin", "anon");
    
    filterChainDefinitionMap.put("/send/*", "anon");
    filterChainDefinitionMap.put("/user/forgot", "anon");
    filterChainDefinitionMap.put("/user/check", "anon");
    
    // 方向跳转接口
    filterChainDefinitionMap.put("/shiro/*", "anon");
    
    // 放行 end ----------------------------------------------------------
    
    // 从数据库或缓存中查取出来的url与resources对应则不会被拦截 放行
    List<Permission> permissionList = permissionService.getInterfacePermissions();
    if (!CollectionUtils.isEmpty(permissionList)) {
      permissionList.forEach(permission -> {
        if (StringUtils.isNotBlank(permission.getUri())) {
          // 注意过滤器配置顺序不能颠倒
          //  认证登录
          //  角色权限 zqRoles：自定义的只需要满足其中一个角色即可访问  ;  roles[admin,guest] : 默认需要每个参数满足才算通过，相当于hasAllRoles()方法
          //  zqPerms:认证自定义的url过滤器拦截权限  【注：多个过滤器用 , 分割】
          filterChainDefinitionMap.put(permission.getUri(), "authc, zqPerms[" + permission.getName() + "]");
        }
      });
    }
    // 拦截剩余所有接口, 必须认证通过才可以访问
    filterChainDefinitionMap.put("/**", "authc");
    log.info("动态生成拦截规则：");
    filterChainDefinitionMap.forEach((entry, key) -> log.info("{} : {}", entry, key));
    return filterChainDefinitionMap;
  }
  
  @Override
  public void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, Long roleId, Boolean isRemoveSession) {
    synchronized (this) {
      AbstractShiroFilter shiroFilter;
      try {
        shiroFilter = shiroFilterFactoryBean.getObject();
      } catch (Exception e) {
        throw new GlobalException("从 shiroFilterFactoryBean 中获取 ShiroFilter 失败!");
      }
      PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
      DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
      
      // 清空拦截管理器中的存储
      manager.getFilterChains().clear();
      // 清空拦截工厂中的存储,如果不清空这里,还会把之前的带进去
      //            ps:如果仅仅是更新的话,可以根据这里的 map 遍历数据修改,重新整理好权限再一起添加
      shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
      // 动态查询数据库中所有权限
      shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitionMap());
      // 重新构建生成拦截
      Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
      for (Map.Entry<String, String> entry : chains.entrySet()) {
        manager.createChain(entry.getKey(), entry.getValue());
      }
      log.info("--------------- 动态生成url权限成功！ ---------------");
      
      // 动态更新该角色相关联的用户shiro权限
      if (roleId != null) {
        updatePermissionByRoleId(roleId, isRemoveSession);
      }
    }
  }
  
  @Override
  public void updatePermissionByRoleId(Long roleId, Boolean isRemoveSession) {
    // 查询当前角色的用户shiro缓存信息 -> 实现动态权限
//    List<UserRole> userRoles = userRoleService.listByRoleId(roleId);
//    if(Objects.isNull(userRoles)){
//      return;
//    }
//    Set<Long> userIds = userRoles.stream()
//      .map(UserRole::getUserId)
//      .collect(Collectors.toSet());
////     删除当前角色关联的用户缓存信息,用户再次访问接口时会重新授权 ; isRemoveSession为true时删除Session -> 即强制用户退出
//    ShiroUtil.deleteCache(userIds, isRemoveSession);
    log.info("--------------- 动态修改用户权限成功！ ---------------");
  }
  
}
