package com.tao.demo.controller.sys;

import com.tao.demo.core.controller.BaseController;
import com.tao.demo.domain.entity.UserRole;
import com.tao.demo.service.UserRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户_角色 前端控制器
 *
 * @author LiTao
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/user-role")
public class UserRoleController extends BaseController<UserRoleService, UserRole> {

}
