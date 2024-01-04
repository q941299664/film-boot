package com.tao.demo.service.impl;

import com.tao.demo.domain.entity.Movie;
import com.tao.demo.mapper.MovieMapper;
import com.tao.demo.service.MovieService;
import com.github.yulichang.base.MPJBaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电影表 服务实现类
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Service
public class MovieServiceImpl extends MPJBaseServiceImpl<MovieMapper, Movie> implements MovieService {

}
