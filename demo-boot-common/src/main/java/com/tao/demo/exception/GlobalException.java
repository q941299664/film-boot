package com.tao.demo.exception;


import com.tao.demo.enums.REnum;

/**
 * @description: 自定义异常类
 * @author: DT
 * @date: 2021/4/19 21:44
 * @version: v1.0
 */
public class GlobalException extends RuntimeException {
  
  private static final long serialVersionUID = 1L;
  
  /**
   * 错误码
   */
  protected String errorCode;
  /**
   * 错误信息
   */
  protected String errorMsg;
  
  public GlobalException() {
    super();
  }
  
  public GlobalException(REnum resultEnum) {
    errorCode = String.valueOf(resultEnum.getCode());
    errorMsg = resultEnum.getMessage();
  }
  
  
  public GlobalException(String errorMsg) {
    super(errorMsg);
    this.errorCode = REnum.UNKNOWN_ERROR.getCode().toString();
    this.errorMsg = errorMsg;
  }
  
  public GlobalException(String errorCode, String errorMsg) {
    super(errorCode);
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }
  
  public GlobalException(String errorCode, String errorMsg, Throwable cause) {
    super(errorCode, cause);
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }
  
  
  public String getErrorCode() {
    return errorCode;
  }
  
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
  
  public String getErrorMsg() {
    return errorMsg;
  }
  
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
  
  @Override
  public Throwable fillInStackTrace() {
    return this;
  }
}
