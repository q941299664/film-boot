package com.tao.demo.core.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @projectName: film
 * @package: cn.fdzc.edu.film.entity.vo.base
 * @className: BaseEntity
 * @author: DemoTao
 * @description: BaseEntity 基础实体类，包含通用字段
 * @date: 2023/12/19 17:17
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@FieldNameConstants
public class BaseEntity implements Serializable, Comparable<BaseEntity> {
  @Serial
  private static final long serialVersionUID = 3363479821720052532L;
  
  public BaseEntity(Long userId) {
    this.createId = userId;
    this.updateId = userId;
  }
  
  /**
   * 数据库主键ID（自增）
   */
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;
  
  /**
   * 逻辑删除标志，0 表示未删除，1 表示已删除
   */
  @TableField(fill = FieldFill.INSERT)
  @TableLogic(value = "0", delval = "1")
  private Boolean deleted = false;
  
  /**
   * 更新者ID
   */
  @TableField(value = "update_id")
  private Long updateId;
  
  /**
   * 更新时间，插入和更新时自动填充
   */
  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;
  
  /**
   * 创建者ID，插入时自动填充
   */
  @TableField(value = "create_id", fill = FieldFill.INSERT)
  private Long createId;
  
  /**
   * 创建时间，插入时自动填充
   */
  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;
  
  /**
   * 比较两个BaseEntity对象的ID大小
   *
   * @param o 需要比较的BaseEntity对象
   * @return 如果当前对象的ID小于o的ID，返回负整数；如果等于o的ID，返回0；如果大于o的ID，返回正整数
   */
  @Override
  public int compareTo(BaseEntity o) {
    return this.getId().compareTo(o.getId());
  }
}
