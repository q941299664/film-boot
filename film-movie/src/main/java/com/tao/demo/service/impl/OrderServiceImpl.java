package com.tao.demo.service.impl;

import com.tao.demo.domain.entity.Order;
import com.tao.demo.mapper.OrderMapper;
import com.tao.demo.service.OrderService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Service
public class OrderServiceImpl extends MPJBaseServiceImpl<OrderMapper, Order> implements OrderService {

}
