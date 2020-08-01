package com.yzl.yujudge.utils.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yzl.yujudge.dto.JudgeResultDTO;

import javax.persistence.AttributeConverter;

/**
 * @author yuzhanglong
 */
public class JudgeResultConverter implements AttributeConverter<JudgeResultDTO, String> {
    private final ObjectMapper mapper;

    public JudgeResultConverter(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String convertToDatabaseColumn(JudgeResultDTO object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("B0001");
        }
    }

    @Override
    public JudgeResultDTO convertToEntityAttribute(String s) {
        // 防止json内容为NULL时报错
        if (s == null) {
            return null;
        }
        try {
            return mapper.readValue(s, JudgeResultDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("B0001");
        }
    }
}
