package org.auto.plate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.auto.plate.mapper")
public class PlateApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlateApplication.class, args);
    }

}
