package com.tao.demo.mapper;

import com.tao.demo.domain.entity.Cinema;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 影院表 Mapper 接口
 * </p>
 *
 * @author LiTao
 * @since 2024-01-04
 */
@Mapper
public interface CinemaMapper extends MPJBaseMapper<Cinema> {

}
