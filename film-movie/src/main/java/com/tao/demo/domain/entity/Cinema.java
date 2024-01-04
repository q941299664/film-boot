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
 * 影院表
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("film_cinema_tb")
public class Cinema extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 影院名称
     */
    @TableField("name")
    private String name;

    /**
     * 影院地址
     */
    @TableField("address")
    private String address;

    public static final String NAME = "name";

    public static final String ADDRESS = "address";
}
