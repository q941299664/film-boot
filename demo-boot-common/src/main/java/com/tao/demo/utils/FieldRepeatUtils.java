package com.tao.demo.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yulichang.toolkit.SpringContentUtils;
import com.tao.demo.core.domain.BaseEntity;
import com.tao.demo.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author xiaokang
 * @description
 * @date 2020/12/29 15:07
 */
@Component
@Slf4j
public class FieldRepeatUtils<T> {
    
    IService<T> baseService;

    /**
     * 实体类中id字段
     */
    private String idColumnName;

    /**
     * 实体类中id的值
     */
    private Object idColumnValue;
    /**
     * @param fields  验证的字段数组
     * @param message 如果不满足返回的消息
     * @param serviceClass 服务类名
     * @param o 实体类
     * @return
     */
    public  boolean fieldRepeat(String [] fields, String message, Class<? extends IService<? extends BaseEntity>> serviceClass, Object o){
        try {
            // 没有校验的值返回true
            if(fields != null && fields.length == 0){
                return true;
            }
            //noinspection unchecked
            this.baseService = (IService<T>) SpringContentUtils.getBean(serviceClass);
            checkUpdateOrSave(o);
            checkRepeat(fields,o,message);
            return true;
        }catch (Exception e){
            String msg = "验证字段是否重复报错";
            log.error(msg,e);
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 通过传入的实体类中 @TableId 注解的值是否为空，来判断是更新还是保存
     * 将值id值和id列名赋值
     * id的值不为空 是更新 否则是插入
     * @param o 被注解修饰过的实体类
     * @return
     */
    public void checkUpdateOrSave(Object o){
        BaseEntity baseEntity = (BaseEntity) o;
        idColumnName = BaseEntity.Fields.id;
        idColumnValue = baseEntity.getId();
    }
/**
 * 获取本类及其父类的属性的方法
 * @param clazz 当前类对象
 * @return 字段数组
 */
private static Field[] getAllFields(Class<?> clazz) {
    List<Field> fieldList = new ArrayList<>();
    while (clazz != null){
        fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
        clazz = clazz.getSuperclass();
    }
    Field[] fields = new Field[fieldList.size()];
    return fieldList.toArray(fields);
}
    
    private Class<T> baseEntityClass;

    /**
     * 通过传入的字段值获取数据是否重复
     * @param fields
     * @param o
     * @param message
     * @return
     */
    public void checkRepeat(String [] fields,Object o,String message){
        QueryWrapper<T> entityWrapper = new QueryWrapper<>();
        Map<String,Object> queryMap = getColumns(fields, o);
      for (Map.Entry<String, Object> entry : queryMap.entrySet()) {
        entityWrapper.eq(entry.getKey(), entry.getValue());
      }
        if(idColumnValue != null){
            //更新的话，那条件就要排除自身
            entityWrapper.ne(idColumnName,idColumnValue);
        }
        List<T> list = baseService.list(entityWrapper);
        if(list != null && !list.isEmpty()){
            throw new GlobalException(message);
        }
    }

    /**
     * 多条件判断唯一性，将我们的属性和值组装在map中，方便后续拼接条件
     * @param fields
     * @param o
     * @return
     */
    public Map<String,Object> getColumns(String [] fields,Object o){
        Field[] fieldList = getAllFields(o.getClass());
        Map<String,Object> map = new HashMap<>();
        for (Field f : fieldList) {
            // ② 设置对象中成员 属性private为可读
            f.setAccessible(true);
            // 判断字段是否包含在数组中，如果存在，则将它对应的列字段放入map中
            if(ArrayUtils.contains(fields,f.getName())){
                getMapData(map,f,o);
            }
        }
        return map;
    }

    /**
     * 得到查询条件
     * @param map  列字段
     * @param f 字段
     * @param o 传入的对象
     */
    private void getMapData( Map<String,Object> map,Field f,Object o){
        try {
            if(f.isAnnotationPresent(TableField.class)){
                TableField tableField = f.getAnnotation(TableField.class);
                Object val = f.get(o);
                map.put(tableField.value(),val);
            }
        }catch (IllegalAccessException i){
            throw new GlobalException("获取字段的值报错");
        }
    }
}