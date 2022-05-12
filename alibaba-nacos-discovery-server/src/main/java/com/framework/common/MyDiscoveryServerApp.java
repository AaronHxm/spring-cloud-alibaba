package com.framework.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Aaron
 *
 * create at:  2022/4/21  14:29
 *
 * description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MyDiscoveryServerApp {

  public static void main(String[] args) {
    SpringApplication.run(MyDiscoveryServerApp.class,args);
  }
  @Slf4j
  @RestController
  static class TestController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name")  String name) {
      log.info("invoked name = " + name);
      return "hello " + name;
    }
  }
}

