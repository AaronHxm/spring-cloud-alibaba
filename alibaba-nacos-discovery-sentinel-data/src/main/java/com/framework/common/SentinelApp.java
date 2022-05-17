package com.framework.common;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Aaron
 *
 * create at:  2022/4/23  10:44
 *
 * description:
 */
@SpringBootApplication
public class SentinelApp {

  public static void main(String[] args) {
    SpringApplication.run(SentinelApp.class, args);
  }
  @Slf4j
  @RestController
  static class TestController {

    @GetMapping("/hello")
    public String hello() {
      return "sentinel-data-nacos";
    }

  }
}

