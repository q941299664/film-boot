package com.tao.demo.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author DemoTao
 */

@Log4j2
@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // 设置允许跨域的路径
    registry.addMapping("/**")
      // 设置允许跨域请求的域名
      .allowedOriginPatterns("*")
      // 是否允许cookie
      .allowCredentials(true)
      // 设置允许的请求方式
      .allowedMethods("*")
      // 设置允许的header属性
      .allowedHeaders("*")
      // 跨域允许时间
      .maxAge(3600);
    
    log.info("启动跨域设置完成!");
  }
  
}