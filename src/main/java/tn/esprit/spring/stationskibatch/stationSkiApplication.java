package tn.esprit.spring.stationskibatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication

public class stationSkiApplication {

    public static void main(String[] args) {
        SpringApplication.run(stationSkiApplication.class, args);
    }

}
