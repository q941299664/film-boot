package com.tao.demo.controller.film;

import com.tao.demo.domain.entity.Cinema;
import com.tao.demo.service.CinemaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tao.demo.core.controller.BaseController;

/**
 * 影院表 前端控制器
 *
 * @author LiTao
 * @since 2024-01-04
 */
@RestController
@RequestMapping("/cinema")
public class CinemaController extends BaseController<CinemaService, Cinema> {

}
