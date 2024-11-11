package kr.co.bootcal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("kr.co.bootcal.mapper")
public class BootCalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootCalApplication.class, args);
    }
}
