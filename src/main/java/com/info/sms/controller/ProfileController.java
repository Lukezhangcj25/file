package com.info.sms.controller;

import com.info.sms.dto.PaginationDTO;
import com.info.sms.mapper.QuestionMapper;
import com.info.sms.model.User;
import com.info.sms.servie.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Luke 2020/4/20 16:32
 */
@Controller
public class ProfileController {
    @Autowired(required = false)
    private QuestionService questionService;

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5") Integer size,
                          Model model) {

        User user = (User)request.getSession().getAttribute("user");

        if(user==null){
            return "redirect:/";
        }

        if(action.equals("")||action.equals(null)){
            model.addAttribute("section", "null");
            model.addAttribute("sectionName", "信息中心");
        }else if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }


        PaginationDTO pagination = questionService.list(user.getId(),page,size);
        model.addAttribute("pagination",pagination);

        int totalCount = questionMapper.countByUserId(user.getId());
        model.addAttribute("totalCount",totalCount);

        return "profile";
    }
}
