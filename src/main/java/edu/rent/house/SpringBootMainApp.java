package edu.rent.house;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.rent.house.dao")
public class SpringBootMainApp {

    public static void main(String[] args) {

       SpringApplication.run(SpringBootMainApp.class,args);
    }
}
