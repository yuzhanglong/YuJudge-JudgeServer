package com.yzl.yujudge.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * 软删除全局实体类
 *
 * @author yuzhanglong
 * @date 2020-8-21 18:45:18
 */
@MappedSuperclass
public class SoftDeleteEntity extends BaseEntity {
    @Basic
    @Column(name = "delete_time")
    private Date deleteTime;

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
