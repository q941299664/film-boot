package com.tao.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.yulichang.annotation.EntityMapping;
import com.tao.demo.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

/**
 * <p>
 * 用户_角色
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Data
@Accessors(chain = true)
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_role_tb")
public class UserRole extends BaseEntity {
  public UserRole(Long userId, Long roleId) {
    super(userId);
    this.userId = userId;
    this.roleId = roleId;
  }
  
  /**
   * 用户ID
   */
  @TableField("user_id")
  private Long userId;
  
  /**
   * 用户信息
   */
  @TableField(exist = false)
  @EntityMapping(tag = User.class, thisField = Fields.userId, joinField = BaseEntity.Fields.id)
  private User user;
  
  /**
   * 角色ID
   */
  @TableField("role_id")
  private Long roleId;
  
  /**
   * 角色信息
   */
  @TableField(exist = false)
  @EntityMapping(tag = Role.class, thisField = Fields.roleId, joinField = BaseEntity.Fields.id)
  private Role role;
  
  public static final String USER_ID = "user_id";
  
  public static final String ROLE_ID = "role_id";
}
