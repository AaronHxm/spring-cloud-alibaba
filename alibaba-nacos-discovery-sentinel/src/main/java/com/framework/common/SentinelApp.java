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
      Entry entry = null;
      try {
        /**
         * 使用HelloWorld
         */
        entry = SphU.entry("HelloWorld");
        /*您的业务逻辑 - 开始*/
        System.out.println("hello world");
        return "aaron.hu";
        /*您的业务逻辑 - 结束*/
      } catch (BlockException e1) {
        /*流控逻辑处理 - 开始*/
        System.out.println("block!");
        return "sentinel block";
        /*流控逻辑处理 - 结束*/
      } finally {
        if (entry != null) {
          entry.exit();
        }
      }

    }

  }

  /**
   * init 规则
   */
  @PostConstruct
  public void initFlowRules() {
    List<FlowRule> rules = new ArrayList<>();
    FlowRule rule = new FlowRule();
    /**
     * 定义资源 HElloWorld
     */
    rule.setResource("HelloWorld");
    //使用 qps规则
    rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
    // Set limit QPS to 2. 每秒2次
    rule.setCount(2);
    rules.add(rule);
    FlowRuleManager.loadRules(rules);

  }
  @SentinelResource(value = "sayHello")
  public String sayHello(String name) {
    return "Hello, " + name;
  }
}

