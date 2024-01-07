package com.tao.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
  
  private static ApplicationContext ac;
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ac = applicationContext;
  }
  
  /**
   * 根据beanName获取bean
   * @param clazz bean的class
   * @return bean
   * @param <T> bean的类型
   */
  public static <T> T getBean(Class<T> clazz) {
    return ac.getBean(clazz);
  }
  /**
   * 根据beanName获取bean
   * @param name bean的名称
   * @param clazz bean的class
   * @return bean
   * @param <T> bean的类型
   */
  public static <T> T getBean(String name, Class<T> clazz) {
    return ac.getBean(name, clazz);
  }
  
}
