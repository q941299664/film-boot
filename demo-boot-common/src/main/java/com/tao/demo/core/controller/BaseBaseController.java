package com.tao.demo.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.extension.mapping.base.MPJDeepService;
import com.tao.demo.core.domain.BaseEntity;
import com.tao.demo.utils.MybatisUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;


/**
 * 基础Controller，其他Controller继承此类
 * 1.继承BaseController需要在类上声明 M--继承了IService，T--entity类
 * 2.模糊查询的条件需要封装到相应的Vo中，需要有get和set方法，需要添加无参构造方法
 * 3.将pojo属性名转化为数据库字段名，要求java中驼峰命名，数据库中为下划线分割，例如hotelName---hotel_name
 * 4.swagger统一命名，无法改变
 * 5.Controller层与Vo层命名需要统一，例如 TestController---TestVo，放在平级包下，com.gxa.admin.controller---com.gxa.admin.vo
 *
 * @author DemoTao
 */

@Log4j2
public abstract class BaseBaseController<S extends MPJDeepService<T>, T extends BaseEntity> implements BaseControllerInterface<T, Integer> {
  /**
   * 通过@Resource注解注入baseService
   */
  @Autowired
  protected S baseService;
  
  /**
   * 新增实体接口
   *
   * @param vo 实体
   * @return 新增结果
   */
  @PostMapping("/save")
  public T baseSave(@RequestBody T vo) {
    return baseService.save(vo) ? vo : null;
  }
  
  /**
   * 批量新增实体接口
   *
   * @param voList 实体
   * @return 批量新增结果
   */
  @PostMapping("/save/batch")
  public List<T> baseSaveBatch(@RequestBody List<T> voList) {
    return baseService.saveBatch(voList) ? voList : null;
  }
  
  /**
   * 根据id删除实体接口
   *
   * @param id 主键id
   * @return 删除结果
   */
  @PostMapping("/remove")
  public boolean baseRemoveById(@RequestParam Integer id) {
    return baseService.removeById(id);
  }
  
  /**
   * 根据ids列表批量删除实体接口
   *
   * @param ids 主键id
   * @return 删除结果
   */
  @PostMapping("/remove/batch")
  public boolean baseRemoveBatchByIds(@RequestBody List<Integer> ids) {
    return baseService.removeByIds(ids);
  }
  
  /**
   * 更新实体接口
   *
   * @param vo 实体
   * @return 更新结果
   */
  @PostMapping("/update")
  public boolean baseUpdateById(@RequestBody T vo) {
    return baseService.updateById(vo);
  }
  
  /**
   * 批量更新实体接口
   *
   * @param vo 实体
   * @return 批量更新结果
   */
  @PostMapping("/update/batch")
  public boolean baseUpdateBatchById(@RequestBody List<T> vo) {
    return baseService.updateBatchById(vo);
  }
  
  /**
   * 根据id获取实体接口
   *
   * @param id 主键id
   * @return 实体
   */
  @GetMapping("/one")
  public T baseGetById(@RequestParam Integer id) {
    return baseService.getById(id);
  }
  
  /**
   * 分页模糊查询接口
   *
   * @param page 页码
   * @param size 每页大小
   * @param vo   查询条件
   * @return 分页列表
   */
  @GetMapping("/page")
  public IPage<T> basePage(@RequestParam Integer page, @RequestParam Integer size, @RequestBody(required = false) T vo) {
    IPage<T> iPage = new Page<>(page, size);
    QueryWrapper<T> queryWrapper = MybatisUtil.entity2QueryWrapper(vo);
    if (Objects.isNull(queryWrapper)) {
      return baseService.page(iPage);
    }
    queryWrapper.orderByDesc("updateTime");
    return baseService.page(iPage, queryWrapper);
  }
  
  /**
   * 查询所有记录
   *
   * @param vo 查询条件
   * @return 所有记录
   */
  @GetMapping("/query")
  public List<T> baseList(@RequestBody(required = false) T vo) {
    return baseService.list();
  }
  
}