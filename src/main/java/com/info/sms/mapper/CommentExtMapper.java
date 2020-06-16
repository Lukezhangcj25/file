package com.info.sms.mapper;

import com.info.sms.model.Comment;
import com.info.sms.model.CommentExample;
import com.info.sms.model.Question;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "commentExtMapper")
public interface CommentExtMapper {
    int incComment(Comment comment);
}