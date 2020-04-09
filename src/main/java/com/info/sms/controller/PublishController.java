package com.info.sms.controller;

import com.info.sms.mapper.QuestionMapper;
import com.info.sms.mapper.UserMapper;
import com.info.sms.model.Question;
import com.info.sms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Luke 2020/4/8 16:06
 */
@Controller
public class PublishController {

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String gublish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {

        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }


        if (user == null) {
            model.addAttribute("errorUserIsNull", "用户未登录!");
            return "publish";
        } else {
            Question question = new Question();
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreater(user.getId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setModifier(user.getId());
            question.setGmtModified(question.getGmtCreate());
            if (title == null) {
                model.addAttribute("errorTitleIsNull", "标题不能为空!");
                return "publish";
            } else if (tag == null) {
                model.addAttribute("errorTagIsNull", "标签不能为空!");
            } else {
                model.addAttribute("200","保存成功!");
                questionMapper.create(question);
            }
        }
        return "redirect:/";
    }
}
