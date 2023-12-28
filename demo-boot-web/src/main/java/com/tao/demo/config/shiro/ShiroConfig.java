package com.tao.demo.config.shiro;


import com.tao.demo.config.shiro.filter.MyAuthorizationFilter;
import com.tao.demo.config.shiro.filter.MyPermissionsAuthorizationFilter;
import com.tao.demo.config.shiro.filter.MyRolesAuthorizationFilter;
import com.tao.demo.config.shiro.manager.StatelessDefaultSubjectFactory;
import com.tao.demo.config.shiro.realm.CustomRealm;
import com.tao.demo.service.ShiroService;
import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;


/**
 * @program: zlgl
 * @description: Shiro配置类:将SecurityManager以及Realm都注入到Spring容器中
 * @author: LiuZhiliang
 * @create: 2021-05-10 08:56
 **/
@Log4j2
@Configuration
public class ShiroConfig {
  
  
  /**
   * @Description: 代理生成器，需要借助SpringAOP来扫描@RequiresRoles和@RequiresPermissions等注解。生成代理类实现功能增强，从而实现权限控制。需要配合AuthorizationAttributeSourceAdvisor一起使用，否则权限注解无效。
   * @Param:
   * @return:
   * @Author: Liuzhiliang
   * @Date:
   */
  @Bean
  public DefaultAdvisorAutoProxyCreator lifecycleBeanProcessor() {
    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
    return defaultAdvisorAutoProxyCreator;
  }
  
  /**
   * @Description: 上面配置的DefaultAdvisorAutoProxyCreator相当于一个切面，下面这个类就相当于切点了，两个一起才能实现注解权限控制。
   * @Param:
   * @return:
   * @Author: Liuzhiliang
   * @Date:
   */
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }
  
  /**
   * @return org.apache.shiro.web.mgt.DefaultWebSubjectFactory
   * @author lhc
   * @description // 自定义subject工厂
   * @date 4:50 下午 2021/4/19
   * @params []
   */
  @Bean
  public DefaultWebSubjectFactory subjectFactory() {
    return new StatelessDefaultSubjectFactory();
  }
  
  /**
   * @return org.apache.shiro.session.mgt.SessionManager
   * @author lhc
   * @description // 自定义session管理器
   * @date 5:50 下午 2021/4/19
   * @params []
   **/
  @Bean
  public SessionManager sessionManager() {
    DefaultSessionManager shiroSessionManager = new DefaultSessionManager();
    // 关闭session校验轮询
    shiroSessionManager.setSessionValidationSchedulerEnabled(false);
    return shiroSessionManager;
  }
  
  /**
   * 权限管理，配置主要是Realm的管理认证
   *
   * @Description: Filter工厂，设置对应的过滤条件和跳转条件
   * @Param:
   * @return:
   * @Author: Liuzhiliang
   * @Date:
   */
  
  @Bean
  public DefaultWebSecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    // 禁用session
    DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
    defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
    subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
    securityManager.setSubjectDAO(subjectDAO);
    // 设置自定义subject工厂
    securityManager.setSubjectFactory(subjectFactory());
    // 设置自定义session管理器
    securityManager.setSessionManager(sessionManager());
    // //设置自定义的realm
    securityManager.setRealm(customRealm());
    //自定义的shiro缓存管理器
    securityManager.setCacheManager(redisCacheManager());
    
    ThreadContext.bind(securityManager);
    return securityManager;
  }
  
  /**
   * 配置Cache管理器：用于往Redis存储权限和角色标识  (使用的是shiro-redis开源插件)
   */
  @Bean
  public CacheManager redisCacheManager() {
    RedisCacheManager redisCacheManager = new RedisCacheManager();
    redisCacheManager.setRedisManager(redisManager());
    return redisCacheManager;
  }
  
  /**
   * Redis管理器
   *
   * @return RedisManager
   */
  @Bean
  public RedisManager redisManager() {
    RedisManager redisManager = new RedisManager();
    redisManager.setDatabase(15);
    return redisManager;
  }
  
  /**
   * SessionID生成器
   */
  @Bean
  public SessionIdGenerator sessionIdGenerator() {
    return new JavaUuidSessionIdGenerator();
  }
  
  /**
   * 配置RedisSessionDAO (使用的是shiro-redis开源插件)
   */
  @Bean
  public RedisSessionDAO redisSessionDAO() {
    RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
    redisSessionDAO.setRedisManager(redisManager());
    redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
    return redisSessionDAO;
  }
  
  
  /**
   * 将自己的RedisManager加入加入容器
   *
   * @return RedisManager
   */
  @Bean
  public CustomRealm customRealm() {
    CustomRealm realm = new CustomRealm();
    realm.setCacheManager(redisCacheManager());
    return realm;
  }
  
  @Resource
  private ShiroService shiroService;
  
  @Bean
  public ShiroFilterFactoryBean shiroFilter(DefaultSecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    // 存放自定义的filter
    LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
    // 定义过滤器名称 【注：map里面key值对于的value要为authc才能使用自定义的过滤器】
    filtersMap.put("zqPerms", new MyPermissionsAuthorizationFilter());
    filtersMap.put("zqRoles", new MyRolesAuthorizationFilter());
    filtersMap.put("authc", new MyAuthorizationFilter());
    shiroFilterFactoryBean.setFilters(filtersMap);
    shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroService.loadFilterChainDefinitionMap());
    shiroFilterFactoryBean.setLoginUrl("/shiro/toLogin");
    shiroFilterFactoryBean.setUnauthorizedUrl("/shiro/toLogin");
    return shiroFilterFactoryBean;
  }
  
}
