package com.tao.demo.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tao.demo.utils.ShiroUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Log4j2
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
  
  //插入时的填充策略
  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("开始插入填充 ....");
    Long userId = ShiroUtil.getCurrLoginUserId();
    //三个参数：字段名，字段值，元对象参数
    this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
    this.setFieldValByName("createId", userId, metaObject);
    this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    this.setFieldValByName("updateId", userId, metaObject);
    this.setFieldValByName("deleted", false, metaObject);
  }
  
  //修改时的填充策略
  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("开始更新填充 ....");
    this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    this.setFieldValByName("updateId", ShiroUtil.getCurrLoginUserId(), metaObject);
  }
}
