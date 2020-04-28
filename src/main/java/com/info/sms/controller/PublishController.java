package com.info.sms.controller;

import com.info.sms.dto.QuestionDTO;
import com.info.sms.model.Question;
import com.info.sms.model.User;
import com.info.sms.servie.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Luke 2020/4/8 16:06
 */
@Controller
public class PublishController {

    @Autowired(required = false)
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id") Integer id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String gublish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("id") Integer id,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空!");
            return "publish";
        } else if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空!");
            return "publish";
        } else if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空!");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");



        if (user == null) {
            model.addAttribute("error", "用户未登录!");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);

        questionService.createOrUpdate(question);

        return "redirect:/";

    }
}