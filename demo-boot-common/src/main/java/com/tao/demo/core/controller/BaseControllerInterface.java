package com.tao.demo.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tao.demo.core.domain.BaseEntity;

import java.util.List;

/**
 * @author DemoTao
 * @Description 声明泛型基类, 包括单表的增删改查
 * @Date 2021/4/13  10:41
 */
public interface BaseControllerInterface<T extends BaseEntity, K> {
  
  /**
   * 新增
   *
   * @param vo 实体
   * @return 新增结果
   */
  T baseSave(T vo);
  
  /**
   * 批量新增
   *
   * @param vo 实体
   * @return 批量新增结果
   */
  List<T> baseSaveBatch(List<T> vo);
  
  /**
   * 根据id删除
   *
   * @param id 主键id
   * @return 删除结果
   */
  boolean baseRemoveById(K id);
  
  /**
   * 批量删除
   *
   * @param ids 主键id集合
   * @return 批量删除结果
   */
  boolean baseRemoveBatchByIds(List<K> ids);
  
  /**
   * 更新
   *
   * @param vo 实体
   * @return 更新结果
   */
  boolean baseUpdateById(T vo);
  
  /**
   * 批量更新
   *
   * @param vo 实体
   * @return 批量更新结果
   */
  boolean baseUpdateBatchById(List<T> vo);
  
  /**
   * 根据id查询
   *
   * @param id 主键id
   * @return 查询结果
   */
  T baseGetById(K id);
  
  /**
   * 分页查询
   *
   * @param page 页码
   * @param size 每页大小
   * @param vo   查询条件
   * @return 查询结果
   */
  IPage<T> basePage(Integer page, Integer size, T vo);
  
  /**
   * 查询所有
   *
   * @param vo 查询条件
   * @return 查询结果
   */
  List<T> baseList(T vo);
}
