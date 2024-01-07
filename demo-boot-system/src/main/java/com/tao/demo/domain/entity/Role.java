package com.tao.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tao.demo.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role_tb")
public class Role extends BaseEntity {
  
  
  /**
   * 上一级角色ID
   */
  @TableField("parent_id")
  private Long parentId;
  
  /**
   * 角色名称
   */
  @TableField(value = "name", condition = SqlCondition.LIKE)
  private String name;
  
  @TableField("is_default")
  private Boolean isDefault;
  
  public static final String PARENT_ID = "parent_id";
  
  public static final String NAME = "name";
  public static final String IS_DEFAULT = "is_default";
}
