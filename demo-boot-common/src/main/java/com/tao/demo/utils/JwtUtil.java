package com.tao.demo.utils;

import com.google.common.collect.Maps;
import com.tao.demo.exception.GlobalException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Log4j2
public class JwtUtil {
  
  /**
   * key的大小必须大于或等于256bit,需要32位英文字符，一个英文字符为：8bit,一个中文字符为12bit
   */
  private final static String KEY = "zxcvbsssssdfdsafasfdssdfsfsfssfs";
  /**
   * 设置加密算法
   */
  private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
  
  /**
   * 过期时间(秒）
   */
  private final static Long EXPIRATION = 60 * 60 * 24L;
  
  private final static String ID_KEY = "id";
  
  /**
   * 获取转换后的私钥对象
   *
   * @return
   */
  public static SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(KEY.getBytes());
  }
  
  /**
   * 生成JWT
   *
   * @param exp 指定过期时间，不能小于当前时间
   * @param id  主键
   * @return
   */
  public static String createJwt(Object id) {
    Map<String, Object> payLoad = Maps.newHashMap();
    payLoad.put("id", id);
    long timeMillis = System.currentTimeMillis();
    return Jwts.builder()
      .setClaims(payLoad)
      .setIssuedAt(new Date(timeMillis))
      .setExpiration(new Date(timeMillis + EXPIRATION * 1000))
      .signWith(getSecretKey(), SIGNATURE_ALGORITHM)
      .compact();
  }
  
  /**
   * 解析JWS，返回一个布尔结果
   *
   * @param jwsString
   * @return
   */
  public static Boolean parseJwt(String jwsString) {
    boolean result = false;
    try {
      Jwts.parserBuilder()
        .setSigningKey(getSecretKey())
        .build()
        .parseClaimsJws(jwsString);
      result = true;
    } catch (JwtException e) {
      log.error("解析JWT失败");
    } catch (Exception e) {
      log.error("解析JWT失败");
      throw new GlobalException("无效登录凭证 ");
    }
    return result;
  }
  
  /**
   * 解析Jws,返回一个Jws对象
   *
   * @param jwsString
   * @return
   */
  public static Jws<Claims> parseJwtResultJws(String jwsString) {
    return Jwts.parserBuilder()
      .setSigningKey(getSecretKey())
      .build()
      .parseClaimsJws(jwsString);
  }
  
  /**
   * 获取header中的数据
   *
   * @param jwsString
   * @return
   */
  public static Header getHeader(String jwsString) {
    return parseJwtResultJws(jwsString).getHeader();
  }
  
  /**
   * 获取PayLoad中携带的数据
   *
   * @param jwsString
   * @return
   */
  public static Map<String, Object> getPayLoad(String jwsString) {
    return parseJwtResultJws(jwsString).getBody();
  }
  
  public static Long getPayLoadWithId(String jwsString){
    return Long.parseLong(String.valueOf(getPayLoad(jwsString).get(ID_KEY)));
  }
  
  /**
   * 获取除去exp和iat的数据，exp：过期时间，iat：JWT生成的时间
   *
   * @param jwsString
   * @return
   */
  public static Map<String, Object> getPayLoadALSOExcludeExpAndIat(String jwsString) {
    Map<String, Object> map = getPayLoad(jwsString);
    map.remove("exp");
    map.remove("iat");
    return map;
  }
  
}


