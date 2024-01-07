package com.tao.demo.repeat;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tao.demo.core.domain.BaseEntity;
import com.tao.demo.repeat.impl.FieldRepeatClass;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author xiaokang
 * @description
 * @date 2020/12/29 14:52
 */

@Documented
/**
 * 指定注解运用的地方:
 *      ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 *      ElementType.CONSTRUCTOR 可以给构造方法进行注解
 *      ElementType.FIELD 可以给属性进行注解
 *      ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 *      ElementType.METHOD 可以给方法进行注解
 *      ElementType.PACKAGE 可以给一个包进行注解
 *      ElementType.PARAMETER 可以给一个方法内的参数进行注解
 *      ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 */
@Target({ElementType.TYPE})
@Constraint(validatedBy = FieldRepeatClass.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldRepeat {
    /**
     * 需要校验的字段
     * @return 字段数组
     */
    String [] fields() default {};
    
    /**
     * 校验不通过的消息
     * @return 消息内容
     */
    String message() default "内容已存在";
    
    /**
     * 服务类
     * @return 服务类
     */
    Class<? extends IService<? extends BaseEntity>>  serviceClass();
    
    /**
     * 分组
     * @return 分组
     */
    Class<?>[] groups() default {};
    
    /**
     * 负载
     * @return 负载
     */
    Class<? extends Payload>[]  payload() default {};
}