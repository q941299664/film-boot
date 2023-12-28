package com.tao.demo.domain.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.domain.vo
 * @className: LoginVO
 * @author: DemoTao
 * @description: 登录视图模型
 * @date: 2023/12/25 20:53
 * @version: 1.0
 */
@Data
public class LoginVO implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
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
  
}
