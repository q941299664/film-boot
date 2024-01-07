package com.tao.demo.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @projectName: film
 * @package: cn.fdzc.edu.film.utils
 * @className: MybatisUtil
 * @author: DemoTao
 * @description: mybatis相关工具类
 * @date: 2023/12/19 23:29
 * @version: 1.0
 */
public class MybatisUtil {
  /**
   * 将ids字符串转为 id列表
   * @param ids id字符串，以逗号分隔
   * @return id列表
   */
  public static <T extends Long> List<T> ids2List(String ids) {
    return ids2Array(ids, ",");
  }
  
  /**
   * 将ids字符串转为 id列表
   * @param ids id字符串
   * @param separator 分隔符
   * @return id列表
   */
  public static <T extends Long> List<T> ids2Array(String ids, String separator) {
    return Arrays.stream(ids.split(separator)).map(Long::parseLong).map(value->(T) value).collect(Collectors.toList());
  }
  
}
