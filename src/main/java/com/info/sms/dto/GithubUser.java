package com.info.sms.dto;

import lombok.Data;

/**
 * Created by Luke 2020/4/1 15:29
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatar_url;
}
