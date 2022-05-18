package com.framework.demo.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : Aaron
 *
 * create at:  2022/5/18  10:45
 *
 * description:
 */
@EnableDubbo
@SpringBootApplication
public class DubboApp {

  public static void main(String[] args) {
    SpringApplication.run(DubboApp.class,args);
  }
}

