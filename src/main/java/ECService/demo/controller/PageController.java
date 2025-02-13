package ECService.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/apply")
    public String apply() {
        return "apply/applyForm";
    }

    @GetMapping("/lookUP")
    public String lookUp() {
        return "apply/applyLookUp";
    }
}
