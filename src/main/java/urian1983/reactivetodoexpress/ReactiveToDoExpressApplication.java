package urian1983.reactivetodoexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@@ComponentScan(basePackages = {
        "urian1983.reactivetodoexpress",
        "controller", "service", "repository", "exception", "mapper", "webform", "config"
})    
public class ReactiveToDoExpressApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveToDoExpressApplication.class, args);
    }
}
