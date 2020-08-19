package com.yzl.yujudge.vo;

import java.util.List;
import java.util.Map;

/**
 * @author yuzhanglong
 * @date 2020-8-19 14:09:38
 * @description 判题机提交相关数据统计视图层对象
 */
public class CountSubmissionByTimeVO {
    private Long totalAmount;
    private List<Map<String, Object>> items;

    public CountSubmissionByTimeVO(Long totalAmount, List<Map<String, Object>> items) {
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CountJudgeHostSubmissionVO{" +
                "hours=" + totalAmount +
                ", items=" + items +
                '}';
    }
}
