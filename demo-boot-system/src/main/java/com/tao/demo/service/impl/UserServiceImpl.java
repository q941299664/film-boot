package com.tao.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.tao.demo.domain.bo.UserRoleBo;
import com.tao.demo.domain.entity.*;
import com.tao.demo.domain.vo.MenuVO;
import com.tao.demo.domain.vo.UserInfoVO;
import com.tao.demo.enums.REnum;
import com.tao.demo.exception.GlobalException;
import com.tao.demo.mapper.UserMapper;
import com.tao.demo.service.RolePermissionService;
import com.tao.demo.service.RoleService;
import com.tao.demo.service.UserRoleService;
import com.tao.demo.service.UserService;
import com.tao.demo.utils.HttpServletUtils;
import com.tao.demo.utils.JwtUtil;
import com.tao.demo.utils.UserCacheUtil;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author LiTao
 * @since 2023-12-24
 */
@Log4j2
@Service
public class UserServiceImpl extends MPJBaseServiceImpl<UserMapper, User> implements UserService {
  
  @Resource
  UserRoleService userRoleService;
  
  @Resource
  RoleService roleService;
  
  @Resource
  RolePermissionService rolePermissionService;
  
  @Override
  public User register(User user) {
    int insert = baseMapper.insert(user);
    if (insert <= 0)
      throw new GlobalException(REnum.REGISTER_INVALID_ERROR);
    // 获取默认角色列表
    List<Role> defaultRoles = roleService.getDefaultRoles();
    Set<UserRole> userRoles = defaultRoles.stream().map(role -> new UserRole(user.getId(), role.getId())).collect(Collectors.toSet());
    boolean b = userRoleService.saveBatch(userRoles);
    if (!b) {
      throw new GlobalException(REnum.ADD_ROLE_INVALID_ERROR);
    }
    return user;
  }
  
  @Override
  public Boolean isEmailExists(String email) {
    return new LambdaQueryChainWrapper<>(baseMapper)
      .eq(User::getEmail, email)
      .exists();
  }
  
  @Override
  public User getByEmail(String email) {
    return new LambdaQueryChainWrapper<>(baseMapper)
      .eq(User::getEmail, email)
      .one();
  }
  
  @Override
  public void setLoginUserCache(User user) {
    Long userId = user.getId();
    
    // 获取用户角色信息
    LambdaQueryWrapper<UserRole> userWrapper = new LambdaQueryWrapper<>(UserRole.class)
      .eq(Objects.nonNull(userId), UserRole::getUserId, userId);
    List<UserRole> userRoles = userRoleService.listDeep(userWrapper);
    // 用户角色列表
    Set<String> roles = userRoles.stream().map(UserRole::getRole)
      .map(Role::getName)
      .collect(Collectors.toSet());
    log.info("设置角色列表缓存: {}", roles);
    // 设置用户角色列表缓存
    UserCacheUtil.setUserRoleCache(userId, String.join(",", roles));
    
    // 角色ID列表
    Set<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
    // 获取用户权限信息
    LambdaQueryWrapper<RolePermission> roleWrapper = new LambdaQueryWrapper<>(RolePermission.class)
      .in(!roleIds.isEmpty(), RolePermission::getRoleId, roleIds);
    Set<String> rolePermissions = rolePermissionService.listDeep(roleWrapper)
      .stream()
      .map(RolePermission::getPermission)
      .map(Permission::getName)
      .collect(Collectors.toSet());
    log.info("设置权限列表缓存: {}", roles);
    // 设置用户权限列表缓存
    UserCacheUtil.setUserPermissionCache(userId, String.join(",", rolePermissions));
  }
  
  @Override
  public UserRoleBo getUserRole(Long userId) {
    return baseMapper.selectJoinOne(UserRoleBo.class,
      new MPJLambdaWrapper<User>()
        .selectAll(User.class)
        .eq(User::getId, userId)
        .innerJoin(UserRole.class, UserRole::getUserId, User::getId)
        .innerJoin(Role.class, Role::getId, UserRole::getRoleId)
        .selectCollection(Role.class, UserRoleBo::getRoles)
    );
  }
  
  @Override
  public UserInfoVO getCurrLoginUser() {
    String token = HttpServletUtils.getRequestHeader(HttpServletUtils.TOKEN_KEY);
    log.info("当前登录用户token：{}",token);
    Long userId = JwtUtil.getPayLoadWithId(token);
    return baseMapper.selectJoinOne(UserInfoVO.class,
      new MPJLambdaWrapper<User>()
        .selectAll(User.class)
        .eq(User::getId, userId)
        .leftJoin(UserRole.class, UserRole::getUserId, User::getId)
        .leftJoin(Role.class, Role::getId, UserRole::getRoleId)
        .selectCollection(Role.class, UserInfoVO::getRoles)
        .leftJoin(RolePermission.class, RolePermission::getRoleId, Role::getId)
        .leftJoin(Permission.class, Permission::getId, RolePermission::getId)
        .selectCollection(Permission.class, UserInfoVO::getPermissions)
    );
  }
  
  @Override
  public MenuVO getMenuByCurrUser() {
    String token = HttpServletUtils.getRequestHeader(HttpServletUtils.TOKEN_KEY);
    log.info("当前登录用户token：{}",token);
    Long userId = JwtUtil.getPayLoadWithId(token);
    return baseMapper.selectJoinOne(MenuVO.class,
      new MPJLambdaWrapper<User>()
        .selectAll(User.class)
        .eq(User::getId, userId)
        .eq(Permission::getParentId, 0)
        .leftJoin(UserRole.class, UserRole::getUserId, User::getId)
        .leftJoin(RolePermission.class, RolePermission::getRoleId, UserRole::getRoleId)
        .leftJoin(Permission.class, Permission::getId, RolePermission::getPermissionId)
        .selectCollection(Permission.class, UserInfoVO::getPermissions,p->p
          .collection(Permission.class,Permission::getChildren))
    );
  }
  
  
}
