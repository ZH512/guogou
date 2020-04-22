package com.neu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */

@SpringBootApplication()
@EnableSwagger2
@MapperScan(basePackages = "com.neu.dao")
public class App 
{
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        System.out.println( "该服务的名称是:{}"+context.getEnvironment().getProperty("spring.application.name"));
        System.out.println("该服务的启动端口是:{}"+context.getEnvironment().getProperty("server.port"));
    }
}