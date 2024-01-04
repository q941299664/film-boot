package com.tao.demo.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.tao.demo.utils.HttpServletUtils;
import com.tao.demo.utils.JwtUtil;
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
    String token = HttpServletUtils.getRequestHeader(HttpServletUtils.TOKEN_KEY);
    log.info("当前登录用户token：{}",token);
    Long userId = JwtUtil.getPayLoadWithId(token);
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
    String token = HttpServletUtils.getRequestHeader(HttpServletUtils.TOKEN_KEY);
    log.info("当前登录用户token：{}",token);
    Long userId = JwtUtil.getPayLoadWithId(token);
    this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    this.setFieldValByName("updateId", userId, metaObject);
  }
}
