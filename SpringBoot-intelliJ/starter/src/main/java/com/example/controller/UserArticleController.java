package com.example.controller;

import org.springframework.stereotype.Controller;

@Controller
public class UserArticleController {

    @ResponseBody
    public String showMain() {
        return "d";
    }

}
