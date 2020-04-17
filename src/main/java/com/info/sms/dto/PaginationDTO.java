package com.info.sms.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luke 2020/4/15 17:43
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevions;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;


    public void setPagination(Integer totalCount, Integer page, Integer size) {

        // 如果总数据数  取余 每页显示数量为0，页数为数据总数除以每页数量的值
        // 如果总数据数  取余 每页显示数量不为0，页数为数据总数除以每页数量的值 + 1
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
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


        // 是否显示上一页
        if (page == 1) {
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
        if (pages.contains(1)) {
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
