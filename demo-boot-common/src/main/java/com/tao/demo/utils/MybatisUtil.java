package com.tao.demo.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


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
   * 数据表实体类转 QueryWrapper
   *
   * @param obj
   * @param <T>
   * @return
   */
  public static <T> QueryWrapper<T> entity2QueryWrapper(Object obj) {
    Class<?> aClass = obj.getClass();
    Field[] fields = obj.getClass().getDeclaredFields();
    QueryWrapper<T> wrapper = new QueryWrapper<>();
    //遍历属性
    for (Field field : fields) {
      Method method;
      try {
        String fieldName = field.getName();
        //跳过serialVersionUID
        if ("serialVersionUID".equals(fieldName)) {
          continue;
        }
        //获取属性上的注解
        TableField fieldAnnotation = field.getAnnotation(TableField.class);
        TableId idAnnotation = field.getAnnotation(TableId.class);
        
        String value;
        //拿到列名
        if (idAnnotation != null) {
          value = idAnnotation.value();
        } else if (fieldAnnotation != null) {
          value = fieldAnnotation.value();
        } else {
          //如果没有这两个注解都为 null 则跳过
          continue;
        }
        if (fieldAnnotation.exist()) {
        
        }
        // 拿到condition
        String condition = fieldAnnotation.condition();
        
        //get方法
        method = aClass.getDeclaredMethod("get" + captureName(fieldName), null);
        Object returnValue = method.invoke(obj);
        if (returnValue instanceof String str) {
          wrapper.eq(StringUtils.isNotBlank(str), value, returnValue);
        } else {
          wrapper.eq(returnValue != null, value, returnValue);
        }
        
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }
    return wrapper;
  }
  
  /**
   * 将字符串的首字母转大写
   *
   * @param str 需要转换的字符串
   * @return
   */
  private static String captureName(String str) {
    // 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
    char[] cs = str.toCharArray();
    cs[0] -= 32;
    return String.valueOf(cs);
  }
  
}
