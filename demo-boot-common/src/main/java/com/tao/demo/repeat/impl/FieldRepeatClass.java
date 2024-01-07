package com.tao.demo.repeat.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tao.demo.core.domain.BaseEntity;
import com.tao.demo.exception.GlobalException;
import com.tao.demo.repeat.FieldRepeat;
import com.tao.demo.utils.FieldRepeatUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaokang
 * @description
 * @date 2020/12/29 14:55
 */
public class FieldRepeatClass implements ConstraintValidator<FieldRepeat, Object>  {

    @Autowired
    FieldRepeatUtils<? extends BaseEntity> fieldRepeatUtils;

    private String [] fields;
    private String message;

    private Class<? extends IService<? extends BaseEntity>> serviceClass;
    @Override
    public void initialize(FieldRepeat validator) {
        this.fields = validator.fields();
        this.message = validator.message();
        this.serviceClass = validator.serviceClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) throws GlobalException {
        try{
          return fieldRepeatUtils.fieldRepeat(fields, message, serviceClass, o);
        }catch (GlobalException e){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }
    }
}