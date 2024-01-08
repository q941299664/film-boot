package com.tao.demo.advice;


import com.tao.demo.core.domain.vo.R;
import com.tao.demo.enums.REnum;
import com.tao.demo.exception.GlobalException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.UnknownSessionException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理以及返回值的统一封装
 *
 * @projectName: signBack
 * @package: cn.fdzc.edu.film.controller
 * @className: ResponseAdvice
 * @author: DemoTao
 * @description: 全局异常处理以及返回值的统一封装
 * @date: 2023/8/20 15:18
 * @version: 1.0
 */
@Log4j2
@ResponseBody
@RestControllerAdvice(value = "com.tao.demo.controller")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }
  
  /**
   * 统一包装
   *
   * @return 统一返回结果体
   */
  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    if (body instanceof R) {
      return body;
    }
    return R.success(body);
  }
  
  
  /**
   * 忽略参数异常处理器;触发例子:带有@RequestParam注解的参数未给参数
   *
   * @param e 忽略参数异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler(MalformedJwtException.class)
  public Object parameterMissingExceptionHandler(MalformedJwtException e) {
    log.error("Jwt解析失败", e);
    return R.error(REnum.INVALID_TOKEN);
  }
  
  /**
   * 忽略参数异常处理器;触发例子:带有@RequestParam注解的参数未给参数
   *
   * @param e 忽略参数异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public Object parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
    log.error("缺少Servlet请求参数异常", e);
    return R.error(REnum.MISSING_SERVLET_REQUEST_PARAMETER_ERROR);
  }
  
  /**
   * 缺少请求体异常处理器;触发例子:不给请求体参数
   *
   * @param e 缺少请求体异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public Object parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
    log.error("参数请求体异常", e);
    return R.error(REnum.HTTP_MESSAGE_NOT_READABLE_ERROR);
  }
  
  /**
   * 统一处理请求参数绑定错误(实体对象传参);
   *
   * @param e BindException
   * @return 自定义错误返回体
   */
  
  @ExceptionHandler(BindException.class)
  public Object validExceptionHandler(BindException e) {
    log.error("方法参数绑定错误(实体对象传参)", e);
    return R.error(REnum.BIND_ERROR);
  }
  
  /**
   * 统一处理自定义的异常;
   *
   * @param e GlobalException
   * @return 自定义错误返回体
   */
  @ExceptionHandler(GlobalException.class)
  public Object validExceptionHandler(GlobalException e) {
    log.error("自定义异常", e);
    return R.error(Integer.valueOf(e.getErrorCode()), e.getErrorMsg());
  }
  
  /**
   * 统一处理请求参数绑定错误(实体对象请求体传参);
   *
   * @param e 参数验证异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public Object parameterExceptionHandler(MethodArgumentNotValidException e) {
    List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
    String message = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
    log.error("方法参数无效异常(实体对象请求体传参): {}", message, e);
    return R.error(REnum.METHOD_ARGUMENT_NOT_VALID_ERROR, message);
  }
  
  /**
   * 用于捕获@RequestParam/@PathVariable参数触发校验规则抛出的异常;
   *
   * @param e 参数验证异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler({ConstraintViolationException.class})
  public Object parameterExceptionHandler(ConstraintViolationException e) {
    log.error("@RequestParam/@PathVariable参数触发校验规则", e);
    StringBuilder sb = new StringBuilder();
    Set<ConstraintViolation<?>> conSet = e.getConstraintViolations();
    for (ConstraintViolation<?> con : conSet) {
      String message = con.getMessage();
      sb.append(message).append(";");
    }
    return R.error(504, sb.toString());
  }
  
  /**
   * 统一处理Shiro相关错误错误(实体对象请求体传参);
   *
   * @param e 参数验证异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler({LockedAccountException.class})
  public Object parameterExceptionHandler(LockedAccountException e) {
    log.error("捕获 shiro 异常：", e);
    e.printStackTrace();
    return R.error(REnum.NOT_LOGIN);
  }
  
  /**
   * 统一处理Shiro相关错误错误(实体对象请求体传参);
   *
   * @param e 参数验证异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler({UnknownSessionException.class})
  public Object parameterExceptionHandler(UnknownSessionException e) {
    log.error("捕获 shiro 异常：", e);
    e.printStackTrace();
    return R.error(REnum.NOT_LOGIN);
  }
  
  /**
   * 统一处理Shiro相关错误错误(实体对象请求体传参);
   *
   * @param e 参数验证异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler({UnauthenticatedException.class})
  public Object parameterExceptionHandler(UnauthenticatedException e) {
    log.error("捕获 shiro 异常：", e);
    e.printStackTrace();
    return R.error(REnum.NO_AUTHORITY_ERROR);
  }
  
  /**
   * 统一处理Shiro相关错误错误(实体对象请求体传参);
   *
   * @param e 参数验证异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler({UnauthorizedException.class})
  public Object parameterExceptionHandler(UnauthorizedException e) {
    log.error("捕获 shiro 异常：", e);
    e.printStackTrace();
    return R.error(REnum.NO_AUTHORITY_ERROR);
  }
  
  /**
   * 统一处理Shiro相关错误错误(实体对象请求体传参);
   *
   * @param e 参数验证异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler({IncorrectCredentialsException.class})
  public Object parameterExceptionHandler(IncorrectCredentialsException e) {
    log.error("捕获 shiro 异常：", e);
    e.printStackTrace();
    return R.error(REnum.INVALID_TOKEN);
  }
  
  /**
   * 统一处理Shiro相关错误错误(实体对象请求体传参);
   *
   * @param e 参数验证异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler({ExpiredCredentialsException.class})
  public Object parameterExceptionHandler(ExpiredCredentialsException e) {
    log.error("捕获 shiro 异常：", e);
    e.printStackTrace();
    return R.error(REnum.INVALID_TOKEN);
  }
  
  /**
   * 统一处理Shiro相关错误错误(实体对象请求体传参);
   *
   * @param e 参数验证异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler({AccountException.class})
  public Object parameterExceptionHandler(AccountException e) {
    log.error("捕获 shiro 异常：", e);
    e.printStackTrace();
    return R.error(REnum.INVALID_TOKEN);
  }
  
  
  /**
   * 系统内部异常捕获
   *
   * @param e 系统内部异常
   * @return 自定义错误返回体
   */
  @ExceptionHandler(value = Exception.class)
  public Object exceptionHandler(Exception e) {
    log.error("系统内部异常，异常信息", e);
    return R.error();
  }
  
  
}
