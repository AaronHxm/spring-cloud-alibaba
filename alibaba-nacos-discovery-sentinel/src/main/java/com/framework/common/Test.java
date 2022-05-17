package com.framework.common;

import java.math.BigDecimal;

/**
 * @author : Aaron
 *
 * create at:  2022/5/13  14:21
 *
 * description:
 */
public class Test {

  public static void main(String[] args) {

    String s = bigDecimalDivide(1, 3);
    System.out.println(s);

  }

  public static String bigDecimalDivide(Integer dividend,Integer divisor) {
    BigDecimal dividendB = new BigDecimal(dividend.toString()).multiply(new BigDecimal("100"));
    BigDecimal divisorB = new BigDecimal(divisor.toString());
    String answer = dividendB.divide(divisorB,0,BigDecimal.ROUND_HALF_UP)
               .toString();
    return answer;
  }
  public static int calute(int aint,int bint){
    BigDecimal a = new BigDecimal(aint).multiply(new BigDecimal("100"));
    BigDecimal b = new BigDecimal(bint);
    BigDecimal multiply = a.divide(b, 0, BigDecimal.ROUND_HALF_UP);
    int i = multiply.intValue();
    return i;

  }
}

