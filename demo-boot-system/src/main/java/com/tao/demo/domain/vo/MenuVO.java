package com.tao.demo.domain.vo;

import cn.hutool.core.bean.BeanUtil;
import com.tao.demo.core.domain.BaseEntity;
import com.tao.demo.domain.entity.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.domain.vo
 * @className: UserInfoVO
 * @author: DemoTao
 * @description: TODO
 * @date: 2024/1/2 14:49
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO extends BaseEntity {
  /**
   * 路径
   */
  private String path;
  /**
   * 邮箱
   */
  private String name;
  /**
   * 路由元数据
   */
  private MenuMetaVO meta;
  
  /**
   * 子菜单
   */
  private List<MenuVO> children;
  
  public MenuVO(Permission permission){
    BeanUtil.copyProperties(permission, this, "children");
    this.path = permission.getUri();
    List<Permission> children = permission.getChildren();
    if (children == null || children.isEmpty()) {
      return;
    }
    this.children = children.stream().map(MenuVO::new).collect(Collectors.toList());
  }
}
