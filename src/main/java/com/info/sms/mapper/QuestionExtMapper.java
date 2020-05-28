package com.info.sms.mapper;

import com.info.sms.model.Question;
import org.springframework.stereotype.Component;

@Component(value = "questionExtMapper")
public interface QuestionExtMapper {
    int incView(Question record);
    int incComment(Question record);
}