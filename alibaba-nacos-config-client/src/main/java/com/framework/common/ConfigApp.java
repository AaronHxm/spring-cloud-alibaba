package com.framework.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Aaron
 *
 * create at:  2022/4/22  14:19
 *
 * description:
 */
@SpringBootApplication
public class ConfigApp {
  public static void main(String[] args) {
    SpringApplication.run(ConfigApp.class, args);
  }

  @Slf4j
  @RestController
  @RefreshScope
   static class TestController {

    @Value("${my.title:test}")
    private String title;

    @GetMapping("/test")
    public String hello() {
      return title;
    }

  }
}

