package com.tao.demo.mapper;

import com.tao.demo.domain.entity.Order;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Mapper
public interface OrderMapper extends MPJBaseMapper<Order> {

}
