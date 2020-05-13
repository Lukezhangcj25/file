package com.info.sms.controller;

import com.info.sms.dto.QuestionDTO;
import com.info.sms.servie.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Created by Luke 2020/4/24 13:33
 */
@Controller
public class QuestionController {

    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){

        // 增加阅读数
        questionService.incView(id);

        // 获取文件数据
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question",questionDTO);

        return "question";
    }
}
