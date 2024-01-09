package com.tao.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tao.demo.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Getter
@Setter
@FieldNameConstants
@Accessors(chain = true)
@TableName("sys_user_tb")
public class User extends BaseEntity {
  
  
  /**
   * 用户名
   */
  @TableField(value = "username", condition = SqlCondition.LIKE)
  private String username;
  
  /**
   * 密码
   */
  @TableField(value = "password", select = false)
  private String password;
  
  /**
   * 电子邮箱
   */
  @TableField(value = "email", condition = SqlCondition.LIKE)
  private String email;
  
  
  public static final String USERNAME = "username";
  
  public static final String PASSWORD = "password";
  
  
  public static final String EMAIL = "email";
}
