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

    /**
     * 带自动序列化的分页对象
     *
     * @param pageItems   分页对象
     * @param targetClass 目标转化类
     * @author yuzhanglong
     * @date 2020-8-20 14:36:12
     */
    public PaginationVO(Page<T> pageItems, Class<K> targetClass) {
        this.initPaginationData(pageItems);

        List<T> tList = pageItems.getContent();
        EntityToVoListMapper<T, K> mapper = new EntityToVoListMapper<>(tList, targetClass);
        setItems(mapper.getItems());
    }

    /**
     * 不带自动序列化的分页对象
     *
     * @param pageItems 分页对象
     * @author yuzhanglong
     * @date 2020-9-9 14:28:58
     */
    public PaginationVO(Page<T> pageItems, List<K> items) {
        this.initPaginationData(pageItems);
        this.setItems(items);
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
