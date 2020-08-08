package com.yzl.yujudge.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;


/**
 * @author yuzhanglong
 * @date 2020-08-08 13:08:38
 * @description 实体类转视图层对象
 */
public class EntityToVoMapper<T, K> {
    private K viewObject;

    public EntityToVoMapper(T entity, Class<K> voClass) {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        K res = mapper.map(entity, voClass);
        setViewObject(res);
    }

    public K getViewObject() {
        return viewObject;
    }

    public void setViewObject(K viewObject) {
        this.viewObject = viewObject;
    }
}
