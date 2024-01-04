package com.tao.demo.mapper;

import com.tao.demo.domain.entity.Movie;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 电影表 Mapper 接口
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Mapper
public interface MovieMapper extends MPJBaseMapper<Movie> {

}
