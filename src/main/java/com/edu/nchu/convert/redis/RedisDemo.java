package com.edu.nchu.convert.redis;

import org.redisson.Config;
import org.redisson.Redisson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

/**
 * java直接操作redis数据库
 * Created by fujianjian on 2016/11/29.
 */
public class RedisDemo {
//    private final static Logger log = LoggerFactory.getLogger(RedisDemo.class);

    public static void main(String[] args) {
        //init
        Config config = new Config();
        config.setConnectionPoolSize(10);
        config.addAddress("127.0.0.1:6379");
        Redisson redis = Redisson.create(config);
        System.out.println("连接redis 成功");
//        log.info("连接redis 成功");

        //test concurrentMap, will synchronized to redis on put action
        ConcurrentMap<String, Object> map = redis.getMap("concurrentMap");
        map.put("name", "fujianjian");
        map.put("age", 12);
        map.put("sex", "male");

        ConcurrentMap<String, Object> resultMap = redis.getMap("concurrentMap");
        System.out.println("resultMap = "+ resultMap.keySet());
//        log.info("resultMap = {}", resultMap.keySet());

        // test set module
        Set<String> redisSet = redis.getSet("redisSet");
        redisSet.add("xiaoz");
        redisSet.add("lyc");

        Set<String> resultSet = redis.getSet("redisSet");
        System.out.println("resultSet = "+ resultSet.toString());
//        log.info("resultSet = {}", resultSet.toString());

        Queue<String> redisQueue = redis.getQueue("redisQueue");
        redisQueue.add("ni hao");
        redisQueue.add("hello world");
        redisQueue.add("zhangsan");

        //获取队列的头
        String queueHead = redisQueue.peek();
        System.out.println("the redis Queue head: "+ queueHead);
//        log.info("the redis Queue head: {}", queueHead);
        //获取队列的头并删除头
        String removeHead = redisQueue.poll();
        System.out.println("the redis Queue head: "+ removeHead);
//        log.info("the redis Queue head: {}", removeHead);
        String queueHeadAgain = redisQueue.peek();
        System.out.println("the redis Queue head: "+ queueHeadAgain);
//        log.info("the redis Queue head: {}", queueHeadAgain);

        Queue resultQueue = redis.getQueue("redisQueue");
        System.out.println("redisQueue result = "+ resultQueue);
//        log.info("redisQueue result = {}", resultQueue);

        //关闭连接
        redis.shutdown();
    }
}
