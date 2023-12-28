package com.tao.demo.controller;

import com.tao.demo.core.controller.BaseBaseController;
import com.tao.demo.domain.entity.Permission;
import com.tao.demo.service.PermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限表 前端控制器
 *
 * @author LiTao
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseBaseController<PermissionService, Permission> {

}
