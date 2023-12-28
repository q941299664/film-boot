package com.tao.demo.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.tao.demo.domain.entity.Dict;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统字典表 Mapper 接口
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Mapper
public interface DictMapper extends MPJBaseMapper<Dict> {

}
