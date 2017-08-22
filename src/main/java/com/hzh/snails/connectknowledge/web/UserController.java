package com.hzh.snails.connectknowledge.web;

import com.hzh.snails.connectknowledge.common.ResponseCode;
import com.hzh.snails.connectknowledge.common.ServerResponse;
import com.hzh.snails.connectknowledge.dao.UserMapper;
import com.hzh.snails.connectknowledge.domain.User;
import com.hzh.snails.connectknowledge.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @ResponseBody
    public ServerResponse signUp(@ModelAttribute User user, HttpSession session){
        ServerResponse response = userService.signUp(user);
        if(response.getStatus() == 0) {
            session.setAttribute("user", response.getData());
        }
        return response;
    }

    @RequestMapping(value = "signin.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse signIn(@ModelAttribute User user, HttpSession session){
        ServerResponse response = userService.signIn(user);
        if(response.getStatus() == 0) {
            session.setAttribute("user", response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    public String logout(HttpSession session){
        if(session.getAttribute("user") != null)
            session.removeAttribute("user");
        return "redirect:/index";
    }

    @RequestMapping(value = "setAvatar.do", method = RequestMethod.POST)
    public ServerResponse setAvatar(@RequestParam("file") MultipartFile file, HttpSession session){
        ServerResponse res = null;
        User user = (User)session.getAttribute("user");
        if(user == null){
            res = ServerResponse.createByErrorMessage("Need Login");
            res.setStatus(ResponseCode.NEED_LOGIN.getCode());
        }
        else {
            res = userService.setAvatar(file, user);
        }
        return res;
    }
}
