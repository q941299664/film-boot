package com.tao.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.yulichang.annotation.EntityMapping;
import com.tao.demo.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

/**
 * <p>
 * 角色_权限表
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Getter
@Setter
@FieldNameConstants
@Accessors(chain = true)
@TableName("sys_role_permission_tb")
public class RolePermission extends BaseEntity {
  
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
  
  /**
   * 权限ID
   */
  @TableField("permission_id")
  private Long permissionId;
  
  /**
   * 权限信息
   */
  @TableField(exist = false)
  @EntityMapping(tag = Permission.class, thisField = Fields.permissionId, joinField = BaseEntity.Fields.id)
  private Permission permission;
  
  public static final String ROLE_ID = "role_id";
  
  public static final String PERMISSION_ID = "permission_id";
}
