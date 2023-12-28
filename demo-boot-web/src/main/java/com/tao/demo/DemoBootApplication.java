package com.tao.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @projectName: demo-boot
 * @package: com.tao.demo
 * @className: DemoBootApplication
 * @author: DemoTao
 * @description: 对外接口模块, 并开启事务管理
 * @date: 2023/12/22 22:03
 * @version: 1.0
 */

@EnableTransactionManagement
@SpringBootApplication
public class DemoBootApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(DemoBootApplication.class, args);
    System.out.println("""
      (♥◠‿◠)ﾉﾞ  DemoBoot启动成功   ლ(´ڡ`ლ)
       ______                              ______                    _
      |_   _ `.                           |_   _ \\                  / |_
       | | `. \\ .---.  _ .--..--.   .--.   | |_) |   .--.    .--. `| |-'
       | |  | |/ /__\\[ `.-. .-. |/ .'`\\ \\ |  __'. / .'`\\ \\/ .'`\\ \\| |
      _| |_.' /| \\__., | | | | | || \\__. |_| |__) || \\__. || \\__. || |,
      |______.'  '.__.'[___||__||__]'.__.'|_______/  '.__.'  '.__.' \\__/
      """);
  }
}
