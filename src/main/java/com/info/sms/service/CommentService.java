package com.info.sms.service;

import com.info.sms.exception.CustomizeErrorCode;
import com.info.sms.exception.CustomizeException;
import com.info.sms.model.Comment;
import org.springframework.stereotype.Service;

/**
 * Created by Luke 2020/5/14 14:06
 */
@Service
public class CommentService {

    public void insert(Comment comment) {
        if(comment.getParantId() == null || comment.getParantId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
    }
}
