package com.info.sms.model;

import lombok.Data;


/**
 * Created by Luke 2020/4/2 17:34
 */
@Data
public class User {
    private int id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}
