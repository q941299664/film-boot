package com.tao.demo.domain.vo;

import com.tao.demo.core.domain.BaseEntity;
import com.tao.demo.domain.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.domain.vo
 * @className: LoginUserVO
 * @author: DemoTao
 * @description: 返回给前端的登录用户信息
 * @date: 2023/12/25 20:53
 * @version: 1.0
 */
@NoArgsConstructor
@Data
public class LoginUserVO extends BaseEntity {
  /*
   * 用户id
   */
  private Long id;
  /**
   * 用户名
   */
  private String username;
  /**
   * 邮箱
   */
  private String email;
  /**
   * 登录凭证
   */
  private String token;
  
  public LoginUserVO(User user, String token) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.username = user.getUsername();
    this.token = token;
  }
  
  public LoginUserVO(User user) {
    this.id = user.getId();
    this.email = user.getEmail();
    this.username = user.getUsername();
  }
}
