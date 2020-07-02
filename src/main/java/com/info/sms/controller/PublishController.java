package com.info.sms.controller;

import com.info.sms.cache.TagCache;
import com.info.sms.dto.QuestionDTO;
import com.info.sms.model.Question;
import com.info.sms.model.User;
import com.info.sms.service.NotificationService;
import com.info.sms.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id") Long id,
                       HttpServletRequest request,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());

        model.addAttribute("tags", TagCache.get());

        User user = (User)request.getSession().getAttribute("user");
        Long unreadCount = notificationService.unreadCount(user.getId());
        model.addAttribute("unreadCount",unreadCount);

        return "publish";
    }

    @GetMapping("/publish")
    public String pulish(Model model){
        model.addAttribute("tags",TagCache.get());
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("id") Long id,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags",TagCache.get());

        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空!");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空!");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空!");
            return "publish";
        }
        String invalid = TagCache.filterinvalid(tag);
        if(StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","标签：" + invalid + "无效，请输入有效标签!");
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

        questionService.createOrUpdate(question,user);


        return "redirect:/";

    }
}