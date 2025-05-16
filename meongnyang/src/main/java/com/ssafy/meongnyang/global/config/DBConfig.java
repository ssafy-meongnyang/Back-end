package com.ssafy.meongnyang.global.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.meongnyang.api.board.repository")
public class DBConfig {

}
