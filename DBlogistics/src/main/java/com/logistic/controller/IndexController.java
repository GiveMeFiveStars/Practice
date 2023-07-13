package com.logistic.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    @RequestMapping("/test")
    public Model test(Model model){
        model.addAttribute("msg","READ ME!");
        return model;
    }
}
