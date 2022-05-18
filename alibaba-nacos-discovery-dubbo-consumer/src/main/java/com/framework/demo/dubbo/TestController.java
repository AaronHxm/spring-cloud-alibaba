package com.framework.demo.dubbo;

import com.framework.demo.dubbo.service.IDemo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : Aaron
 *
 * create at:  2022/5/18  13:55
 *
 * description:
 */
@RestController
public class TestController {
  @Autowired
  LoadBalancerClient loadBalancerClient;
  @DubboReference
  IDemo demosService ;
  @RequestMapping("/hello")
  public String hello(){

    ServiceInstance serviceInstance = loadBalancerClient.choose("providers:com.framework.demo.dubbo.service.IDemo::");
    int port = serviceInstance.getPort();
    return demosService.say("aaron"+",请求的端口："+port+"。。。。。。");
  }
}

