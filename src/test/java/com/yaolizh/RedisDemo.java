//package com.yaolizh;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController()
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RedisDemo {
//    @SuppressWarnings("rawtypes")
//	@Autowired
//    RedisTemplate redisTemplate;
//
//    @SuppressWarnings("unchecked")
//	@Test
//    public void test() {
//        redisTemplate.opsForValue().set("a", "b");
//        logger.info(redisTemplate.opsForValue().get("a"));
//    }
// 
//}
