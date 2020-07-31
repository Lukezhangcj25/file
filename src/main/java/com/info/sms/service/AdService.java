package com.info.sms.service;

import com.info.sms.mapper.AdMapper;
import com.info.sms.model.Ad;
import com.info.sms.model.AdExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

/**
 * Created by Luke 2020/7/31 16:13
 */
@Service
public class AdService {
    @Autowired
    private AdMapper adMapper;

    public List<Ad> list(){
        AdExample adExample = new AdExample();
        adExample.createCriteria()
                .andStatusEqualTo(1)
                .andGmtStartLessThan(System.currentTimeMillis())
                .andGmtEndGreaterThan(System.currentTimeMillis());
//        adExample.setOrderByClause("priority desc");
        List<Ad> ads = adMapper.selectByExample(adExample);
        return ads;
    }
}
