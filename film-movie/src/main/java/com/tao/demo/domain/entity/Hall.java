package com.tao.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tao.demo.core.domain.BaseEntity;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 放映厅表
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("film_hall_tb")
public class Hall extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 放映厅名称
     */
    @TableField("name")
    private String name;

    /**
     * 放映厅容量
     */
    @TableField("capacity")
    private Integer capacity;

    /**
     * 所属影院ID
     */
    @TableField("cinema_id")
    private Integer cinemaId;

    public static final String NAME = "name";

    public static final String CAPACITY = "capacity";

    public static final String CINEMA_ID = "cinema_id";
}
