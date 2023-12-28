package com.tao.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tao.demo.core.domain.BaseEntity;
import com.tao.demo.enums.PermissionTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_permission_tb")
public class Permission extends BaseEntity {
  
  /**
   * 权限名称
   */
  @TableField("name")
  private String name;
  
  /**
   * 上一级权限ID
   */
  @TableField("parent_id")
  private Long parentId;
  
  /**
   * 权限类型
   */
  @TableField("type")
  private PermissionTypeEnum type;
  
  /**
   * 权限字符串
   */
  @TableField("uri")
  private String uri;
  
  /**
   * 权限元数据
   */
  @TableField("meta")
  private String meta;
  
  /**
   * 排序依据字段
   */
  @TableField("sort")
  private Integer sort;
  
  public static final String NAME = "name";
  
  public static final String PARENT_ID = "parent_id";
  
  public static final String TYPE = "type";
  
  public static final String URI = "uri";
  
  public static final String META = "meta";
  
  public static final String SORT = "sort";
}
