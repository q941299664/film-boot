package com.tao.demo.controller.film;

import com.tao.demo.domain.entity.Hall;
import com.tao.demo.service.HallService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tao.demo.core.controller.BaseController;

/**
 * 放映厅表 前端控制器
 *
 * @author LiTao
 * @since 2024-01-04
 */
@RestController
@RequestMapping("/hall")
public class HallController extends BaseController<HallService, Hall> {

}
