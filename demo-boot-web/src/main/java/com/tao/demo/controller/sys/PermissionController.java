package com.tao.demo.controller.sys;

import com.tao.demo.core.controller.BaseController;
import com.tao.demo.domain.entity.Permission;
import com.tao.demo.domain.vo.MenuVO;
import com.tao.demo.enums.PermissionTypeEnum;
import com.tao.demo.service.PermissionService;
import com.tao.demo.utils.HttpServletUtils;
import com.tao.demo.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限表 前端控制器
 *
 * @author LiTao
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController<PermissionService, Permission> {
  
  /**
   * 获取当前用户的菜单
   * @return 菜单列表
   */
  @GetMapping("/menus")
  public List<MenuVO> getMenus() {
    String token = HttpServletUtils.getRequestHeader(HttpServletUtils.TOKEN_KEY);
    Long userId = JwtUtil.getPayLoadWithId(token);
    List<Permission> permissions = baseService.getPermissionsByTypeAndUserId(PermissionTypeEnum.MENU, userId);
    return permissions.stream().map(MenuVO::new).collect(Collectors.toList());
  }
}
