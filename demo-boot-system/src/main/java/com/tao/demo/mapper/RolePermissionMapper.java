package com.tao.demo.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import com.tao.demo.domain.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色_权限表 Mapper 接口
 * </p>
 *
 * @author LiTao
 * @since 2023-12-25
 */
@Mapper
public interface RolePermissionMapper extends MPJBaseMapper<RolePermission> {

}
