package com.info.sms.dto;

import lombok.Data;

/**
 * Created by Luke 2020/7/28 10:31
 */
@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
