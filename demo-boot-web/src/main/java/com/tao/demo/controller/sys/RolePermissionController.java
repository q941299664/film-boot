package com.tao.demo.controller.sys;

import com.tao.demo.core.controller.BaseController;
import com.tao.demo.domain.entity.RolePermission;
import com.tao.demo.service.RolePermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色_权限表 前端控制器
 *
 * @author LiTao
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/role-permission")
public class RolePermissionController extends BaseController<RolePermissionService, RolePermission> {

}
