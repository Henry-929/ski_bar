package com.g5619.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessegesController {

    @RequestMapping("/hello")
    public String ab(){
        return "chatroom";
    }
}
