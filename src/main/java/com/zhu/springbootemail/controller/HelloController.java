package com.zhu.springbootemail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String info(Model model){
        model.addAttribute("valicode","12845");
        return "emailTemplate";
    }
}
