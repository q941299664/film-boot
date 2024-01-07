package com.tao.demo.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 返回码枚举类
 *
 * @projectName: signBack
 * @package: com.sign.signback.enums
 * @className: REnum
 * @author: DemoTao
 * @description: TODO
 * @date: 2023/8/20 15:02
 * @version: 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum REnum {
  /**
   * 成功
   */
  SUCCESS(200, "成功"),
  UNKNOWN_ERROR(500, "未知错误"),
  MISSING_SERVLET_REQUEST_PARAMETER_ERROR(501, "缺少Servlet请求参数异常"),
  HTTP_MESSAGE_NOT_READABLE_ERROR(502, "参数请求体异常"),
  BIND_ERROR(503, "方法参数绑定错误(实体对象传参)"),
  METHOD_ARGUMENT_NOT_VALID_ERROR(504, "方法参数无效异常(实体对象请求体传参)"),
  CONSTRAINT_VIOLATION(504, "参数校验失败"),
  USER_IS_EXISTS_ERROR(505, "用户已存在"),
  REGISTER_INVALID_ERROR(505, "注册失败"),
  ADD_ROLE_INVALID_ERROR(505, "角色添加失败"),
  USER_NOT_IS_EXISTS_ERROR(506, "用户不存在"),
  PASSWORD_ERROR(506, "密码错误"),
  
  NOT_LOGIN(403, "未登录"),
  LOGIN_FAILURE_ERROR(403, "登录失败"),
  NO_AUTHORITY_ERROR(401, "无权限访问"),
  NOT_TOKEN(403, "没有token"),
  INVALID_TOKEN(403, "无效token"),
  TOKEN_TIMEOUT(403, "token过期"),
  BE_REPLACED(403, "被挤下线"),
  KICK_OUT(403, "被踢下线"),
  ;
  
  
  
  private Integer code;
  private String message;
  
}
