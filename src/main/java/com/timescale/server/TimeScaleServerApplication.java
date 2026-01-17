package com.timescale.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.timescale.server.mapper")
public class TimeScaleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeScaleServerApplication.class, args);
    }

}