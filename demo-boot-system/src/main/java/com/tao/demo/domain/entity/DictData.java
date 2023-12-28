package com.tao.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tao.demo.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统字典数据表
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_dict_data_tb")
public class DictData extends BaseEntity {
  
  /**
   * 上一级字典数据ID
   */
  @TableField("parent_id")
  private Long parentId;
  
  /**
   * 字典编码
   */
  @TableField("code")
  private String code;
  
  /**
   * 字典标签
   */
  @TableField("label")
  private String label;
  
  /**
   * 字典键值
   */
  @TableField("value")
  private String value;
  
  /**
   * 样式属性
   */
  @TableField("css_style")
  private String cssStyle;
  
  /**
   * 表格回显样式
   */
  @TableField("list_style")
  private String listStyle;
  
  /**
   * 是否默认
   */
  @TableField("is_default")
  private Boolean isDefault;
  
  /**
   * 备注
   */
  @TableField("remark")
  private String remark;
  
  /**
   * 排序依据字段
   */
  @TableField("sort")
  private Integer sort;
  
  public static final String PARENT_ID = "parent_id";
  
  public static final String CODE = "code";
  
  public static final String LABEL = "label";
  
  public static final String VALUE = "value";
  
  public static final String CSS_STYLE = "css_style";
  
  public static final String LIST_STYLE = "list_style";
  
  public static final String IS_DEFAULT = "is_default";
  
  public static final String REMARK = "remark";
  
  public static final String SORT = "sort";
}
