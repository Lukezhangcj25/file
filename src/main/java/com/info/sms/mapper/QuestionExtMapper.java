package com.info.sms.mapper;

import com.info.sms.model.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "questionExtMapper")
public interface QuestionExtMapper {
    int incView(Question record);
    int incComment(Question record);
    List<Question> selectRelated(Question question);
}