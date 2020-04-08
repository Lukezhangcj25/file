package com.info.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Luke 2020/4/8 16:06
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String gublish(){
        return "publish";
    }
}
