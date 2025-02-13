package ECService.demo.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
public class FIrstApiController {
    @GetExchange("/api/hello")
    public String hello() {
        return "Hello World";
    }

}
