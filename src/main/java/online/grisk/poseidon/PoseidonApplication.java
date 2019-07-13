package online.grisk.poseidon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:integration.cfg.xml")
public class PoseidonApplication {
    public static void main(String[] args) {
        SpringApplication.run(PoseidonApplication.class, args);
    }

}
