package com.example.jit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.example.jit.mapper*", "com.baomidou.springboot.mapper*"})
public class JitApplication {

    public static void main(String[] args) {

       SpringApplication.run(com.example.jit.JitApplication.class, args);
    }

}
