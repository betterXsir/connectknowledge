package com.hzh.snails.connectknowledge.web;

import com.hzh.snails.connectknowledge.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GreetingController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/signin")
    public String signin(){
        return "signin";
    }
    @GetMapping("/signup")
    public String singup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }
}