package com.info.sms.controller;

import com.info.sms.dto.CommentCreateDTO;
import com.info.sms.dto.ResultDTO;
import com.info.sms.exception.CustomizeErrorCode;
import com.info.sms.model.Comment;
import com.info.sms.model.User;
import com.info.sms.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Luke 2020/5/13 16:31
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){

            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }


        Comment comment = new Comment();
        comment.setParantId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setCreator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        commentService.insert(comment);

        return ResultDTO.successOf();
    }
}
