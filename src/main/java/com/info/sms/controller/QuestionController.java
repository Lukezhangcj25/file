package com.info.sms.controller;

import com.info.sms.dto.CommentDTO;
import com.info.sms.dto.QuestionDTO;
import com.info.sms.enums.CommentTypeEnum;
import com.info.sms.service.CommentService;
import com.info.sms.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by Luke 2020/4/24 13:33
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;



    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           HttpServletRequest request,
                           Model model){

        // 增加阅读数
        questionService.incView(id);

        // 获取文件数据
        QuestionDTO questionDTO = questionService.getById(id);


        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);

        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);


        return "question";
    }
}
