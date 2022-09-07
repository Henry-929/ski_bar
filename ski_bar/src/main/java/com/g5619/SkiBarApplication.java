package com.g5619;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.g5619.mapper")
public class SkiBarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkiBarApplication.class, args);
    }

}
