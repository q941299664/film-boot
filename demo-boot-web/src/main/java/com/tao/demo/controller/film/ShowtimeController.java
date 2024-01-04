package com.tao.demo.controller.film;

import com.tao.demo.domain.entity.Showtime;
import com.tao.demo.service.ShowtimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tao.demo.core.controller.BaseController;

/**
 * 场次表 前端控制器
 *
 * @author LiTao
 * @since 2024-01-04
 */
@RestController
@RequestMapping("/showtime")
public class ShowtimeController extends BaseController<ShowtimeService, Showtime> {

}
