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
 * 系统字典表
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_dict_tb")
public class Dict extends BaseEntity {
  
  
  /**
   * 字典名称
   */
  @TableField(value = "name", condition = SqlCondition.LIKE)
  private String name;
  
  /**
   * 字典编码
   */
  @TableField(value = "code", condition = SqlCondition.LIKE)
  private String code;
  
  /**
   * 排序依据字段
   */
  @TableField("sort")
  private Integer sort;
  
  public static final String NAME = "name";
  
  public static final String CODE = "code";
  
  public static final String SORT = "sort";
}
