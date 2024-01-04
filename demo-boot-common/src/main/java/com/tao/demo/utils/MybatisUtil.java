package com.tao.demo.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

  public static <T extends Long> List<T> ids2List(String ids) {
    return ids2Array(ids, ",");
  }
  public static <T extends Long> List<T> ids2Array(String ids, String separator) {
    return Arrays.stream(ids.split(separator)).map(Long::parseLong).map(value->(T) value).collect(Collectors.toList());
  }
  
}
