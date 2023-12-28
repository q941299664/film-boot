package com.tao.demo.service.utils.common;

import com.tao.demo.utils.PasswordUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.service.utils.common
 * @className: PasswordUtilTest
 * @author: DemoTao
 * @description: TODO
 * @date: 2023/12/26 9:40
 * @version: 1.0
 */
@Log4j2
public class PasswordUtilTest {
  @Test
  void test() {
    String md5Password = PasswordUtil.encrypt("123456");
    log.info("密码长度：{}， 密码：{}", md5Password.length(), md5Password);
  }
}
