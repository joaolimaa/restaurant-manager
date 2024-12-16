package fiap.restaurant_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "fiap.restaurant_manager.adapters.persistence.repository")
//@EntityScan(basePackages = "fiap.restaurant_manager.adapters.persistence.entities")
public class RestaurantManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantManagerApplication.class, args);
    }
}