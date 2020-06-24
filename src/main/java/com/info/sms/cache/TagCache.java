package com.info.sms.cache;

import com.info.sms.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Luke 2020/6/19 17:26
 */
public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setId("program");
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("javascript","前端","vue.js","css","html","node.js","react.js"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setId("framework");
        framework.setCategoryName("框架");
        framework.setTags(Arrays.asList("Spring","SpringBoot","laravel","express","flask","yli","yii"));
        tagDTOS.add(framework);

        TagDTO server = new TagDTO();
        server.setId("server");
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","docker","apache","centos","tomcat","unix","linux","nginx","docker","apache","centos","tomcat","unix","linux","nginx","docker","apache","centos","tomcat","unix","linux","nginx","docker","apache","centos","tomcat","unix"));
        tagDTOS.add(server);

        return tagDTOS;
    }

    public static String filterinvalid(String tags){
        String[] split = StringUtils.split(tags,",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t-> !tagList.contains(t)).collect(Collectors.joining(","));

        return invalid;
    }
}
