package com.tao.demo.config.shiro.filter;

import com.alibaba.fastjson2.JSON;
import com.tao.demo.core.domain.vo.R;
import com.tao.demo.enums.REnum;
import com.tao.demo.utils.JwtUtil;
import com.tao.demo.utils.UserCacheUtil;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author DemoTao
 */
@Log4j2
public class MyRolesAuthorizationFilter extends AuthorizationFilter {
  private static final String TOKEN_KEY = "x-access-token";
  
  @Override
  protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
    response.setContentType("application/Json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().print(JSON.toJSONString(R.error(REnum.LOGIN_FAILURE_ERROR)));
  }
  
  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse resp, Object mappedValue) throws Exception {
    // 转换成http的请求和响应
    HttpServletRequest req = (HttpServletRequest) request;
    String token = req.getHeader(TOKEN_KEY);
    if (token == null) {
      return false;
    }
    Map<String, Object> payLoad;
    try {
      payLoad = JwtUtil.getPayLoad(token);
    } catch (MalformedJwtException e) {
      return false;
    }
    Object id = payLoad.get("id");
    if (id == null) {
      return false;
    }
    Long userId = Long.parseLong(String.valueOf(id));
    String[] roleArray = (String[]) mappedValue;
    List<String> roleList = Arrays.asList(roleArray);
    log.info("校验角色：{}", Arrays.toString(roleArray));
    List<Object> userRoleCache = UserCacheUtil.getUserRoleCache(userId);
    Set<String> roles = userRoleCache.stream().map(o -> (String) o).collect(Collectors.toSet());
    boolean contains = roles.contains(roleList.getFirst());
    log.info("结果：{}", contains);
    return contains;
  }
  
  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
    log.info("角色校验失败：{}", request);
    // 转换成http的请求和响应
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    resp.setContentType("application/Json");
    resp.setCharacterEncoding("UTF-8");
    // 获取请求头的值
    String header = req.getHeader("X-Requested-With");
    // ajax 的请求头里有X-Requested-With: XMLHttpRequest      正常请求没有
    if ("XMLHttpRequest".equals(header)) {
      resp.getWriter().print(JSON.toJSONString(R.error(REnum.LOGIN_FAILURE_ERROR)));
    } else {  //正常请求
      resp.getWriter().print(JSON.toJSONString(R.error(REnum.NO_AUTHORITY_ERROR)));
    }
    return false;
  }
  
}
