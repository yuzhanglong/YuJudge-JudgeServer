package com.yzl.yujudge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.bo.JudgeHostBO;
import com.yzl.yujudge.dto.JudgeHostDTO;
import com.yzl.yujudge.dto.JudgeHostResponseDTO;
import com.yzl.yujudge.model.JudgeHostEntity;
import com.yzl.yujudge.network.JudgeHostCommonRequest;
import com.yzl.yujudge.repository.JudgeHostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 判题服务器的相关业务
 * @date 2020-7-30 19:00
 */

@Service
public class JudgeHostService {
    private final JudgeHostRepository judgeHostRepository;

    public JudgeHostService(JudgeHostRepository judgeHostRepository) {
        this.judgeHostRepository = judgeHostRepository;
    }

    /**
     * @author yuzhanglong
     * @description 获取所有判题服务器信息
     * @date 2020-7-30 19:06
     */
    public List<JudgeHostBO> inspectJudgeHosts() {
        List<JudgeHostBO> judgeHostBOList = new ArrayList<>();
        List<JudgeHostEntity> judgeHostEntities = judgeHostRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        judgeHostEntities.forEach(res -> {
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            JudgeHostBO judgeHost = mapper.map(res, JudgeHostBO.class);
            String address = res.getAddress();
            JudgeHostCommonRequest request = new JudgeHostCommonRequest(address);
            String testResponse = request.testJudgeConnection();
            try {
                JudgeHostResponseDTO responseDTO = objectMapper.readValue(testResponse, JudgeHostResponseDTO.class);
                boolean isSuccess = isConnectSuccess(responseDTO);
                judgeHost.setActive(isSuccess);
            } catch (JsonProcessingException e) {
                // TODO: 走到这里说明judgeHost没有响应，需要日志记录
                // 顺便告诉前端这个服务器处于非正常状态
                judgeHost.setActive(false);
            }
            judgeHostBOList.add(judgeHost);
        });
        return judgeHostBOList;
    }

    /**
     * @author yuzhanglong
     * @description 添加一个judgeHost记录
     * @date 2020-7-30 18:34
     */
    public void createJudgeHost(JudgeHostDTO judgeHostDTO) {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        JudgeHostEntity judgeHostEntity = mapper.map(judgeHostDTO, JudgeHostEntity.class);
        judgeHostRepository.save(judgeHostEntity);
    }


    /**
     * @param responseDTO judgeHost response数据传输对象
     * @author yuzhanglong
     * @return Boolean 是否连接正常
     * @description 判断是否连接正常
     * @date 2020-7-30 23:53
     */
    private Boolean isConnectSuccess(JudgeHostResponseDTO responseDTO){
        String code = responseDTO.getCode();
        return "00000".equals(code);
    }
}
