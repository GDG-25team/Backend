package gdg.team25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Gdg25teamApplication {

    public static void main(String[] args) {
        SpringApplication.run(Gdg25teamApplication.class, args);
    }

}
