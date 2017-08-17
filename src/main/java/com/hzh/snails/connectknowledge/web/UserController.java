package com.hzh.snails.connectknowledge.web;

import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.dao.UserMapper;
import com.hzh.snails.connectknowledge.domain.User;
import com.hzh.snails.connectknowledge.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "signup.do", method = RequestMethod.POST)
    public String signUp(@ModelAttribute User user, HttpSession session, HttpServletRequest request){
        ServerResponse response = userService.signUp(user);
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {//如果没有cookie数组
            System.out.println("没有cookie");
        } else {
            for(Cookie cookie : cookies){
                System.out.println("cookieName:"+cookie.getName()+",cookieValue:"+ cookie.getValue());
            }
        }
        System.out.println("--------serverResponse: " + response.getStatus() + "----" + response.getMsg());
        session.setAttribute("serverResponse", response);
        return "redirect:/index";
    }

    @RequestMapping(value = "signin.do", method = RequestMethod.POST)
    public String signIn(@ModelAttribute User user){
        return userService.signIn(user);
    }
}
