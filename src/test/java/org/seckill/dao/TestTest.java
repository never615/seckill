package org.seckill.dao;

import org.junit.Test;

/**
 * Created by never615 on 6/22/16.
 */
public class TestTest {

    @Test
    public void test1(){
        String verification=""+123+1;
        System.out.println("--------1.:"+verification);
        verification="2"+String.format("%015d",Integer.valueOf(verification));  //核销码构成:type(2)+补足零+userid+seckillId  ;一共16位

        System.out.println("核销码:"+verification);
    }

    @Test
    public void test2(){
        String format = String.format("%06d", 12);
        System.out.println("format:"+format);

    }



}
