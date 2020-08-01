package com.yzl.yujudge.utils.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author yuzhanglong
 * @description listJson的序列化
 * @date 2020-7-21 19:32:35
 */
public class ListJsonConverter implements AttributeConverter<List<Object>, String> {
    private final ObjectMapper mapper;

    public ListJsonConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String convertToDatabaseColumn(List<Object> objects) {
        try {
            return mapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("B0001");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> convertToEntityAttribute(String s) {
        // 防止json内容为NULL时报错
        if (s == null) {
            return null;
        }
        try {
            return mapper.readValue(s, List.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("B0001");
        }
    }
}
