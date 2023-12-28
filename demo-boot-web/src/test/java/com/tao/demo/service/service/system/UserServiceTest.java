package com.tao.demo.service.service.system;

import com.tao.demo.DemoBootApplication;
import com.tao.demo.domain.bo.UserRoleBo;
import com.tao.demo.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo.service
 * @className: system.service.UserServiceTest
 * @author: DemoTao
 * @description: 用户服务层测试
 * @date: 2023/12/25 23:44
 * @version: 1.0
 */
@Log4j2
@SpringBootTest(classes = DemoBootApplication.class)
public class UserServiceTest {
  @Resource
  UserService userService;
  
  @Test
  public void getUserRoleTest() {
    UserRoleBo userRole = userService.getUserRole(1L);
    log.info("用户角色:{}", userRole);
  }
}
