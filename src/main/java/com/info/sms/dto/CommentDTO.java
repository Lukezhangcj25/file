package com.info.sms.dto;

import com.info.sms.model.User;
import lombok.Data;

/**
 * Created by Luke 2020/6/2 15:22
 */
@Data
public class CommentDTO {

    private Long id;
    private Long parantId;
    private Integer type;
    private Long creator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
