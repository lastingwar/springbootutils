package com.lastingwar.springbootutils;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.lastingwar.springbootutils.mapper")
public class SpringbootutilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootutilsApplication.class, args);
    }

}
