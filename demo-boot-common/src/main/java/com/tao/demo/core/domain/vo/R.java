package com.tao.demo.core.domain.vo;

import com.tao.demo.enums.REnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @projectName: film
 * @package: cn.fdzc.edu.film.entry.vo
 * @className: R
 * @author: DemoTao
 * @description: TODO
 * @date: 2023/12/19 14:41
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {
  @Serial
  private static final long serialVersionUID = 3363123821720052532L;
  private Integer code;
  private String msg;
  private T data;
  
  public static <T> R<T> success(String msg, T data) {
    REnum success = REnum.SUCCESS;
    return new R<>(success.getCode(), msg, data);
  }
  
  public static <T> R<T> success(T data) {
    REnum success = REnum.SUCCESS;
    return new R<>(success.getCode(), success.getMessage(), data);
  }
  
  public static <T> R<T> success() {
    REnum success = REnum.SUCCESS;
    return new R<>(success.getCode(), success.getMessage(), null);
  }
  
  public static <T> R<T> error() {
    REnum error = REnum.UNKNOWN_ERROR;
    return new R<>(error.getCode(), error.getMessage(), null);
  }
  
  public static <T> R<T> error(REnum rEnum) {
    return new R<>(rEnum.getCode(), rEnum.getMessage(), null);
  }
  
  public static <T> R<T> error(REnum rEnum, String msg) {
    return new R<>(rEnum.getCode(), msg, null);
  }
  
  public static <T> R<T> error(String msg) {
    REnum customizeError = REnum.CUSTOMIZE_ERROR;
    return new R<>(customizeError.getCode(), msg, null);
  }
  
  public static <T> R<T> error(Integer code, String msg) {
    return new R<>(code, msg, null);
  }
  
  public static <T> R<T> error(Integer code, String msg, T data) {
    return new R<>(code, msg, data);
  }
}