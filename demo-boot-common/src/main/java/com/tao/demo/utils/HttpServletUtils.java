package com.tao.demo.utils;

 
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class HttpServletUtils {
	
	public static final String TOKEN_KEY = "x-access-token";
	
 
	/**
	 * 获取request header中的值
	 * @param name
	 * @return
	 */
	public static String getRequestHeader(String name) {
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
				.getRequest();
		return request.getHeader(name);
	}
	
	/**
	 * 获取cookie的值
	 * @param name
	 * @return
	 */
	public static String getCookie(String name) {
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
				.getRequest();
		Cookie[] cookies = request.getCookies();
		if(null == cookies) {
			return null;
		}
		for(Cookie c : cookies) {
			if(c.getName().equals(name)) {
				return c.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 获取httpsession
	 * @return
	 */
	public static HttpSession getHttpSession() {
		HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
				.getRequest();
		return request.getSession();
	}
	
	/**
	 * 获取客户端ip地址
	 * @param request
	 * @return
	 */
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for"); 
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if(ip.contains(",")){
                ip = ip.split(",")[0];
            }
        }  
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");  
        }  
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();  
        } 
        return ip; 
	}
}