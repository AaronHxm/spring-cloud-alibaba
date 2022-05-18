package com.framework.demo.dubbo.service.impl;

import com.framework.demo.dubbo.service.IDemo;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author : Aaron
 *
 * create at:  2022/5/18  10:33
 *
 * description:
 */
@DubboService
public class DemoServiceImpl implements IDemo {


  @Override
  public String say(String str) {



    return str + " 我是dubbo的实现类";
  }
}

