package com.tao.demo.config.shiro.filter;


import com.alibaba.fastjson2.JSON;
import com.tao.demo.core.domain.vo.R;
import com.tao.demo.enums.REnum;
import com.tao.demo.utils.HttpServletUtils;
import com.tao.demo.utils.JwtUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/**
 * @author DemoTao
 */
@Component
@Log4j2
public class MyAuthorizationFilter extends FormAuthenticationFilter {
  
  
  @Override
  protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
    response.setContentType("application/Json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().print(JSON.toJSONString(R.error(REnum.LOGIN_FAILURE_ERROR)));
  }
  
  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse resp, Object mappedValue) {
    // 转换成http的请求和响应
    HttpServletRequest req = (HttpServletRequest) request;
    String token = req.getHeader(HttpServletUtils.TOKEN_KEY);
    log.info("校验是否登录：{}", token);
    if (token == null) {
      return false;
    }
    if (!JwtUtil.parseJwt(token)) {
      return false;
    }
    SecurityUtils.getSubject().login(new BearerToken(token));
    return true;
  }
  
  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    
    // 转换成http的请求和响应
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    resp.setContentType("application/Json");
    resp.setCharacterEncoding("UTF-8");
    // 检查是否拥有访问权限
    String token = req.getHeader(HttpServletUtils.TOKEN_KEY);
    
    if (!JwtUtil.parseJwt(token)) {
      resp.getWriter().print(JSON.toJSONString(R.error(REnum.INVALID_TOKEN)));
    } else {
      // 获取请求头的值
      String header = req.getHeader("X-Requested-With");
      // ajax 的请求头里有X-Requested-With: XMLHttpRequest      正常请求没有
      if ("XMLHttpRequest".equals(header)) {
        resp.getWriter().print(JSON.toJSONString(R.error(REnum.LOGIN_FAILURE_ERROR)));
      } else {  //正常请求
        resp.getWriter().print(JSON.toJSONString(R.error(REnum.NO_AUTHORITY_ERROR)));
      }
      
    }
    return false;
  }
  
  /**
   * 对跨域访问提供支持
   *
   * @param request request
   * @param response response
   * @return  boolean
   * @throws Exception Exception
   */
  @Override
  protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
    httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
    httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
    // 跨域发送一个option请求
    if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
      httpServletResponse.setStatus(HttpStatus.OK.value());
      return false;
    }
    return super.preHandle(request, response);
  }
}
