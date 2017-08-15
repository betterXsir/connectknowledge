package com.hzh.snails.connectknowledge.web;

import com.hzh.snails.connectknowledge.dao.UserMapper;
import com.hzh.snails.connectknowledge.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "signup.do", method = RequestMethod.POST)
    @ResponseBody
    public String signUp(@ModelAttribute User user){
        user.setUserEmail("lhzgege5277@163.com");
        user.setUserNickname("nick");
        user.setUserStatus(1);
        userMapper.insert(user);
        return "index";
    }
}
