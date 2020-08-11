package com.yzl.yujudge.vo;

import com.yzl.yujudge.utils.EntityToVoListMapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 分页对象的视图层对象
 * @date 2020-7-20 11:35:34
 */
public class PaginationVO<T, K> {
    private Long total;
    private Integer count;
    private Integer page;
    private Integer totalPage;
    private List<K> items;

    public PaginationVO(Page<T> pageItems, Class<K> targetClass) {
        this.initPaginationData(pageItems);

        List<T> tList = pageItems.getContent();
        EntityToVoListMapper<T, K> mapper = new EntityToVoListMapper<>(tList, targetClass);
        setItems(mapper.getItems());
    }

    void initPaginationData(Page<T> pageItems) {
        this.total = pageItems.getTotalElements();
        this.count = pageItems.getSize();
        this.page = pageItems.getNumber();
        this.totalPage = pageItems.getTotalPages();
    }

    public void setItems(List<K> items) {
        this.items = items;
    }


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<K> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "PaginationVO{" +
                "total=" + total +
                ", count=" + count +
                ", page=" + page +
                ", totalPage=" + totalPage +
                ", items=" + items +
                '}';
    }
}
