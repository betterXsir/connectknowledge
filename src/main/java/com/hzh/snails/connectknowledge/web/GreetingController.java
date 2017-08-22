package com.hzh.snails.connectknowledge.web;

import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class GreetingController {
    @RequestMapping("/index")
    public String index(HttpSession session){
        if(session.getAttribute("serverResponse") == null) {
            session.setAttribute("serverResponse", null);
        }
        return "index";
    }

    @GetMapping("/signin")
    public String signin(Model model){
        return "signin";
    }
    @GetMapping("/signup")
    public String singup(Model model){
        return "signup";
    }

    @GetMapping("/setting")
    public String setting(Model model){
        return "setting";
    }

    @GetMapping("/signup1")
    public String singup(){
        return "signup1";
    }
}