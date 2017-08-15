package com.hzh.snails.connectknowledge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hzh.snails.connectknowledge.dao")
public class ConnectknowledgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectknowledgeApplication.class, args);
	}
}
