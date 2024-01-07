package com.tao.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonValue;
import com.github.yulichang.annotation.EntityMapping;
import com.tao.demo.core.domain.BaseEntity;
import com.tao.demo.domain.vo.MenuMetaVO;
import com.tao.demo.enums.PermissionTypeEnum;
import com.tao.demo.repeat.FieldRepeat;
import com.tao.demo.service.PermissionService;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.List;

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
@FieldNameConstants
@FieldRepeat(fields = {"name"}, message = "权限或菜单名称不能重复", serviceClass = PermissionService.class)
public class Permission extends BaseEntity {
  
  /**
   * 权限名称
   */
  @TableField(value = "name", condition = SqlCondition.LIKE)
  private String name;
  /**
   * 上一级权限ID
   */
  @TableField("parent_id")
  private Long parentId;
  /**
   * 子节点
   */
  @TableField(exist = false)
  @EntityMapping(thisField = BaseEntity.Fields.id, joinField = Fields.parentId)
  private List<Permission> children;
  
  /**
   * 权限类型
   * @see PermissionTypeEnum#getPermissionType()
   */
  @JsonValue
  @TableField("permission_type")
  private Integer permissionType;
  
  /**
   * 权限字符串
   */
  @TableField("uri")
  private String uri;
  
  /**
   * 权限元数据
   */
  @TableField(value = "meta", typeHandler = JacksonTypeHandler.class)
  private MenuMetaVO meta;
  
  /**
   * 排序依据字段
   */
  @TableField("sort")
  private Integer sort;
  
  public static final String NAME = "name";
  
  public static final String PARENT_ID = "parent_id";
  
  public static final String PERMISSION_TYPE = "permission_type";
  
  public static final String URI = "uri";
  
  public static final String META = "meta";
  
  public static final String SORT = "sort";
}
