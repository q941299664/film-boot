package com.tao.demo.controller;

import com.tao.demo.core.controller.BaseBaseController;
import com.tao.demo.domain.entity.Role;
import com.tao.demo.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色表 前端控制器
 *
 * @author LiTao
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseBaseController<RoleService, Role> {

}
