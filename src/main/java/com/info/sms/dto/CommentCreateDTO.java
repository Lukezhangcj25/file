package com.info.sms.dto;

import lombok.Data;

/**
 * Created by Luke 2020/5/13 16:44
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
