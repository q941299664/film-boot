package com.tao.demo.controller;

import com.tao.demo.core.controller.BaseBaseController;
import com.tao.demo.core.domain.vo.R;
import com.tao.demo.domain.entity.User;
import com.tao.demo.domain.vo.LoginUserVO;
import com.tao.demo.domain.vo.LoginVO;
import com.tao.demo.domain.vo.RegisterVO;
import com.tao.demo.exception.GlobalException;
import com.tao.demo.service.UserService;
import com.tao.demo.utils.JwtUtil;
import com.tao.demo.utils.PasswordUtil;
import jakarta.validation.Valid;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.subject.Subject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 用户表 前端控制器
 *
 * @author LiTao
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseBaseController<UserService, User> {
  
  /**
   * 用户注册
   *
   * @param registerVO 用户注册信息
   * @return 注册结果
   */
  @Transactional(rollbackFor = Exception.class)
  @PostMapping("/register")
  public R<Object> register(@Valid @RequestBody RegisterVO registerVO) {
    Boolean emailExists = baseService.isEmailExists(registerVO.getEmail());
    if (emailExists) {
      return R.error("邮箱已存在");
    }
    User user = registerVO.getUser();
    String password = user.getPassword();
    String md5Password = PasswordUtil.encrypt(password);
    user.setPassword(md5Password);
    User register = baseService.register(user);
    if (Objects.isNull(register)) {
      throw new GlobalException("注册失败");
    }
    Long id = register.getId();
    // 设置创建和更新者id
    register.setCreateId(id);
    register.setUpdateId(id);
    baseService.updateById(user);
    
    return R.success();
  }
  
  /**
   * 用户登录
   *
   * @param loginVO 用户登录信息
   * @return 登录结果
   */
  @PostMapping("/login")
  public R<LoginUserVO> login(@Valid @RequestBody LoginVO loginVO) {
    String email = loginVO.getEmail();
    User user = baseService.getByEmail(email);
    if (Objects.isNull(user)) {
      return R.error("邮箱不存在");
    }
    String password = loginVO.getPassword();
    String encryptedPassword = user.getPassword();
    
    if (!PasswordUtil.valid(password, encryptedPassword)) {
      return R.error("密码错误");
    }
    String token = JwtUtil.createJwt(user.getId());
    // 创建shiro的身份证器
    BearerToken bearerToken = new BearerToken(token);
    
    Subject subject = SecurityUtils.getSubject();
    subject.login(bearerToken);
    // 设置登录用户的权限、角色列表缓存信息
    baseService.setLoginUserCache(user);
    return R.success(new LoginUserVO(user, token));
  }
}
