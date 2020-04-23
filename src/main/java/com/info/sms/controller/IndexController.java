package com.info.sms.controller;

import com.info.sms.dto.PaginationDTO;
import com.info.sms.servie.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Luke 2020/3/30 10:34
 */
@Controller
public class IndexController {
    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size) {


        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}
