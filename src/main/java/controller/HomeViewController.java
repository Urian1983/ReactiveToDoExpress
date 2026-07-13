package controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
@Hidden
public class HomeViewController {
    @GetMapping("/")
    public Mono<String> home() {
        return Mono.just("redirect:/tasks");
    }
}
