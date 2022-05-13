package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","pdk");
        return "greetings";//templates/greetings.mustache -> 브라우저로 전송!
    }

    @GetMapping("/bye")
    public String seeYouLater(Model model){
        model.addAttribute("nickname","pdk");
        return "goodbye"; //template/goodbye.mustache
    }

    //1: bring All articles
    //2: transfer articles to view
    //3: setting view page
}
