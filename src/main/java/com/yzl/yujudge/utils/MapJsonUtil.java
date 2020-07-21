package com.yzl.yujudge.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhanglong
 * @date 2020-7-21 19:03:29
 * @description 基本Json的序列化和反序列化（数据库 --- Map）
 */
public class MapJsonUtil implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper mapper;

    public MapJsonUtil(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringObjectMap) {
        try {
            return mapper.writeValueAsString(stringObjectMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("B0001");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> convertToEntityAttribute(String string) {
        // 防止json内容为NULL时报错
        if (string == null) {
            return null;
        }
        try {
            return mapper.readValue(string, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("B0001");
        }
    }
}
