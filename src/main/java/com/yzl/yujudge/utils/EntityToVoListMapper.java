package com.yzl.yujudge.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 实体类转换成视图层列表的工具类
 * @date 2020-08-06 21:07:37
 */
public class EntityToVoListMapper<T, K> {
    private List<K> items;

    public EntityToVoListMapper(List<T> entityList, Class<K> targetVoClass) {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<K> kArrayList = new ArrayList<>();
        entityList.forEach(res -> {
            K viewObject = mapper.map(res, targetVoClass);
            kArrayList.add(viewObject);
        });
        setItems(kArrayList);
    }

    public List<K> getItems() {
        return items;
    }

    public void setItems(List<K> items) {
        this.items = items;
    }
}
