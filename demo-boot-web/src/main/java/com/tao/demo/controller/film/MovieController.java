package com.tao.demo.controller.film;

import com.tao.demo.domain.entity.Movie;
import com.tao.demo.service.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tao.demo.core.controller.BaseController;

/**
 * 电影表 前端控制器
 *
 * @author LiTao
 * @since 2024-01-04
 */
@RestController
@RequestMapping("/movie")
public class MovieController extends BaseController<MovieService, Movie> {

}
