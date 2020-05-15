package com.info.sms.service;

import com.info.sms.enums.CommentTypeEnum;
import com.info.sms.exception.CustomizeErrorCode;
import com.info.sms.exception.CustomizeException;
import com.info.sms.mapper.CommentMapper;
import com.info.sms.mapper.QuestionMapper;
import com.info.sms.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Luke 2020/5/14 14:06
 */
@Service
public class CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    public void insert(Comment comment) {
        if(comment.getParantId() == null || comment.getParantId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || comment.getType() == 0 ){
            throw new CustomizeException(CustomizeErrorCode.TYPE_NOT_FOUND);
        }
        if(!CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            // 回复评论
        }else{
            // 回复问题
        }
    }
}
