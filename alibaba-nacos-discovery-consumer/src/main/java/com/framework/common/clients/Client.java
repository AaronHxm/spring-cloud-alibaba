package com.framework.common.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Aaron
 *
 * create at:  2022/4/21  15:11
 *
 * description: feign客户端 使用feign 去调取服务
 */
@FeignClient("alibaba-nacos-discovery-server")
public interface Client {

  @GetMapping("/hello")
  String hello(@RequestParam(name = "name") String name);
}

