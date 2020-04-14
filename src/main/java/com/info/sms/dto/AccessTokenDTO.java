package com.info.sms.dto;

import lombok.Data;

/**
 * Created by Luke 2020/4/1 14:53
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
