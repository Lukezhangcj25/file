package com.info.sms.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Luke 2020/6/19 17:28
 */

@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
    private String id;
}
