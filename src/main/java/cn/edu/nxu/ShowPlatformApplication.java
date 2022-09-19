package cn.edu.nxu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ShowPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowPlatformApplication.class, args);
    }

}
