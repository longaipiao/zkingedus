package com.zking.zkingedu;

import io.netty.util.internal.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class test {
    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.42.128");
        Set<String> keys = jedis.keys("*");
        System.out.println(jedis.get("upwd"));

        System.out.println("完事");
    }

    private static Logger logger = LoggerFactory.getLogger(test.class);

    @Test
    public void test1(){
        log.info("来了info");
    }


    @Test
    public void t1(){
        /*//1.给数组随机赋值
        long[] arrayOfLong = new long [ 20000 ];
        Arrays.parallelSetAll( arrayOfLong,
                     index -> ThreadLocalRandom.current().nextInt( 1000000 ) );
         //2.打印出前10个元素
         Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                     i -> System.out.print( i + " " ) );
         System.out.println();
        //3.数组排序
         Arrays.parallelSort( arrayOfLong );
         //4.打印排序后的前10个元素
        Arrays.stream(arrayOfLong).limit(20).forEach(i ->System.out.println());
         Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                     i -> System.out.print( i + " " ) );*/

        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
                 System.out.println("求和："+nums
                                 .stream()//转成Stream
                                 .filter(team -> team!=null)//过滤
                                 .distinct()//去重
                                 .mapToInt(num->num*2)//map操作
                                 .skip(2)//跳过前2个元素
                                 .limit(4)//限制取前4个元素
                                 .peek(System.out::println)//流式处理对象函数
                                 .sum());//



    }

}
