package com.info.sms.controller;

import com.info.sms.dto.PaginationDTO;
import com.info.sms.model.User;
import com.info.sms.service.NotificationService;
import com.info.sms.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Luke 2020/3/30 10:34
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/")
    public String index(Model model,
                        HttpServletRequest request,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size) {


        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination", pagination);


        return "index";
    }
}
