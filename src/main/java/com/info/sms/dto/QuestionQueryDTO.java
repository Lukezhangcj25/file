package com.info.sms.dto;

import lombok.Data;

/**
 * Created by Luke 2020/7/21 15:08
 */
@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
