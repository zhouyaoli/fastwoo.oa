package com.yaolizh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.annotation.MapperScan;

@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.yaolizh.**.dao")
@SpringBootApplication
@EnableCaching
public class FastwooOaApplication {
	public static void main(String[] args) {
		SpringApplication.run(FastwooOaApplication.class, args);
		System.out.println("ヾ(◍°∇°◍)ﾉﾞ    fastwoo启动成功      ヾ(◍°∇°◍)ﾉﾞ\n");
	}
}
