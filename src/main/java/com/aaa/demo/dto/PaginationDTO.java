package com.aaa.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName 金鑫
 * @Description: TODO
 * @Author yinwe
 * @Date 2020/2/6
 * @Version V1.0
 **/
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    //如果，则返回上一页
    private boolean showPrevious;
    //如果，则返回首页
    private boolean showFirstPage;
    //如果，则返回下一页
    private boolean showNext;
    //如果，则返回尾页
    private boolean showEndPage;
    //显示页数
    private List<Integer> pages = new ArrayList<>();
    //当前页
    private Integer page;
    //共多少页
    private Integer totalPage;
    public void setPagination(Integer totalCount, Integer page,Integer size) {
        /**
         * 对总条数进行判断，如果求余为整则取值，不为整，则+1
         */
        if (totalCount % size ==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        this.page = page;
        pages.add(page);


        /**
         * 判断逻辑：当当前页数小于总条数时候，显示当前页+i
         * 前三条数据判断
         */
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }

            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        // 是否展示下一页
        if (page==totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        // 是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        // 是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
