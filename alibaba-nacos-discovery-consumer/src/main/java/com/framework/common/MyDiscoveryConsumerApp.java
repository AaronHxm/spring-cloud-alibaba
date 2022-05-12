package com.framework.common;

import com.framework.common.clients.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author : Aaron
 *
 * create at:  2022/4/21  15:08
 *
 * description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MyDiscoveryConsumerApp {

  public static void main(String[] args) {
    SpringApplication.run(MyDiscoveryConsumerApp.class, args);
  }


  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  @LoadBalanced
  public WebClient.Builder loadBalancedWebClientBuilder() {
    return WebClient.builder();
  }


  @RestController
  class TestController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    Client client;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/test/feign")
    public String test() {
      String result = client.hello("aaron");
      return "Return feign: " + result;
    }

    @GetMapping("/test/rest")
    public String rest() {
      String result = restTemplate
          .getForObject("http://alibaba-nacos-discovery-server/hello?name=aaron", String.class);
      return "Return : " + result;
    }

    @GetMapping("/test/webclient")
    public Mono<String> webclient() {
      Mono<String> result = webClientBuilder.build()
          .get()
          .uri("http://alibaba-nacos-discovery-server/hello?name=aaron")
          .retrieve()
          .bodyToMono(String.class);
      return result;
    }
  }
}

