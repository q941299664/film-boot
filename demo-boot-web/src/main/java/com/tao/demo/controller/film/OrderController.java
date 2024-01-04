package com.tao.demo.controller.film;

import com.tao.demo.domain.entity.Order;
import com.tao.demo.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tao.demo.core.controller.BaseController;

/**
 * 订单表 前端控制器
 *
 * @author LiTao
 * @since 2024-01-04
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController<OrderService, Order> {

}
