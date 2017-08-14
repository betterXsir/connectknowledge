package com.hzh.snails.connectknowledge.web;

import com.hzh.snails.connectknowledge.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GreetingController {
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World")
                                       String name, Model model){
        User user = new User();
        user.setId(1);
        user.setName(name);
        model.addAttribute("name",name);
        return "example2";
    }
}