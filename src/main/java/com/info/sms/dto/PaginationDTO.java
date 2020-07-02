package com.info.sms.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke 2020/4/15 17:43
 */
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevions;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private boolean pageType;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;
    private boolean showPage;


    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;

        pages.add(page);
        // for循环点击页面前后显示几页
        // if判断点击页面，前后页数显示规则

        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }
        this.page = page;

        if(page > 0){
            pageType = true;
        }else{
            pageType = false;
        }

        if (page < 1) {
            showPrevions = false;
        } else {
            showPage = true;
        }

        // 是否显示上一页
        if (page < 2) {
            showPrevions = false;
        } else {
            showPrevions = true;
        }


        // 是否跳转下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        // 是否显示第一页
        if (pages.contains(1) || pages.contains(0)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }


        // 是否跳转最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
