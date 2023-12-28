package com.tao.demo.domain.vo;

import com.tao.demo.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.domain.vo
 * @className: RegisterVO
 * @author: DemoTao
 * @description: 注册用户视图模型
 * @date: 2023/12/25 18:31
 * @version: 1.0
 */
@Data
public class RegisterVO implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
  
  /**
   * 用户名
   */
  @NotNull(message = "用户名不能为空")
  private String username;
  /**
   * 密码
   */
  @NotNull(message = "密码不能为空")
  private String password;
  /**
   * 邮箱
   */
  @NotNull(message = "邮箱不能为空")
  private String email;
  
  public User getUser() {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setEmail(email);
    return user;
  }
}
