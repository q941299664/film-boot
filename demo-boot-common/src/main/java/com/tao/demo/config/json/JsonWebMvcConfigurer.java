package com.tao.demo.config.json;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @projectName: signBack
 * @package: com.sign.signback.configurer
 * @className: JSONWebMvcConfigurer
 * @author: DemoTao
 * @description: TODO
 * @date: 2023/8/20 16:09
 * @version: 1.0
 */
@Configuration
public class JsonWebMvcConfigurer implements WebMvcConfigurer {
  @Bean
  public FastJsonConfig fastJsonConfig() {
    // 1. 自定义配置
    FastJsonConfig config = new FastJsonConfig();
    config.setDateFormat("yyyy-MM-dd HH:mm:ss");
    
    // 2.1 配置序列化的行为
    config.setWriterFeatures(JSONWriter.Feature.PrettyFormat);
    
    // 2.2 配置反序列化的行为
    config.setReaderFeatures(JSONReader.Feature.FieldBased, JSONReader.Feature.SupportArrayToBean);
    
    return config;
  }
  
  /**
   * 使用 FastJsonHttpMessageConverter 来替换 Spring MVC 默认的 HttpMessageConverter
   * 以提高 @RestController @ResponseBody @RequestBody 注解的 JSON序列化和反序列化速度。
   *
   * @param converters
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.clear();
    // 1. 转换器
    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    converter.setDefaultCharset(StandardCharsets.UTF_8);
    converter.setFastJsonConfig(fastJsonConfig());
    converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
    converters.addFirst(converter);
  }
  
  
}
