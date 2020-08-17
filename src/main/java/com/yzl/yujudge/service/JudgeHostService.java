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
import com.yzl.yujudge.store.redis.JudgeHostCache;
import com.yzl.yujudge.utils.EntityToVoListMapper;
import com.yzl.yujudge.vo.JudgeHostVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yuzhanglong
 * @description 判题服务器的相关业务
 * @date 2020-7-30 19:00
 */

@Service
public class JudgeHostService {
    private final JudgeHostRepository judgeHostRepository;
    private final JudgeHostCache judgeHostCache;

    public JudgeHostService(
            JudgeHostRepository judgeHostRepository,
            JudgeHostCache judgeHostCache) {
        this.judgeHostRepository = judgeHostRepository;
        this.judgeHostCache = judgeHostCache;
    }

    /**
     * @author yuzhanglong
     * @description 获取所有判题服务器信息
     * @date 2020-7-30 19:06
     */

    public List<JudgeHostBO> inspectJudgeHosts(List<JudgeHostEntity> judgeHostEntityList) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<JudgeHostBO> judgeHostBOList = new ArrayList<>();
        for (JudgeHostEntity res : judgeHostEntityList) {
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            JudgeHostBO judgeHost = mapper.map(res, JudgeHostBO.class);
            String address = res.getAddress();
            JudgeHostCommonRequest request = new JudgeHostCommonRequest(address);
            String testResponse = request.testJudgeConnection();
            try {
                JudgeHostResponseDTO responseDTO = objectMapper.readValue(testResponse, JudgeHostResponseDTO.class);
                boolean isSuccess = isConnectSuccess(responseDTO);
                judgeHost.setConnection(isSuccess);
            } catch (JsonProcessingException e) {
                // TODO: 走到这里说明judgeHost没有响应，需要日志记录
                // 顺便告诉前端这个服务器处于非正常状态
                judgeHost.setConnection(false);
            }
            judgeHostBOList.add(judgeHost);
        }
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
     * @return Boolean 是否连接正常
     * @author yuzhanglong
     * @description 判断是否连接正常
     * @date 2020-7-30 23:53
     */
    private Boolean isConnectSuccess(JudgeHostResponseDTO responseDTO) {
        String code = responseDTO.getCode();
        return "00000".equals(code);
    }

    /**
     * @author yuzhanglong
     * @description 获取当前所有判题服务器信息
     * @date 2020-08-16 21:01:41
     */
    public List<JudgeHostVO> getJudgeHostsInfo() {
        List<JudgeHostEntity> judgeHostEntities = judgeHostRepository.findAll();
        EntityToVoListMapper<JudgeHostEntity, JudgeHostVO> mapper = new EntityToVoListMapper<>(judgeHostEntities, JudgeHostVO.class);
        return mapper.getItems();
    }


    /**
     * @param judgeHostBOList 判题机相关业务对象
     * @author yuzhanglong
     * @description 判题机状态的信息, 存入redis中
     * @date 2020-08-17 14:18:59
     */
    public void setJudgeConditionCache(List<JudgeHostBO> judgeHostBOList) {
        judgeHostCache.setJudgeConditionCache(judgeHostBOList);
    }

    /**
     * @author yuzhanglong
     * @description 获取缓存中判题机状态的信息
     * @date 2020-08-17 14:24:55
     */
    public List<JudgeHostBO> getJudgeConditionCache() {
        List<Object> rawList = judgeHostCache.getJudgeConditionCache();
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<JudgeHostBO> judgeHostBOList = new ArrayList<>();
        for (Object o : rawList) {
            judgeHostBOList.add(mapper.map(o, JudgeHostBO.class));
        }
        return judgeHostBOList;
    }

    /**
     * @return 可以请求的judgeHostBO
     * @author yuzhanglong
     * @date 2020-08-17 15:23:23
     * @description 选择一个合适的判题机以发送提交请求
     * 当前我们有多个判题机集群部署, 当前缓存中已经有了所有判题机的信息
     * 我们需要达到的目标
     * 1. 保证程序的稳定性
     * 2. 维持负载均衡
     */
    public JudgeHostBO chooseJudgeHostToRequest() {
        // 从缓存中获取所有判题机的信息
        List<JudgeHostBO> res = getJudgeConditionCache();
        // TODO: 设计一个负载均衡的算法，最终返回一个可以请求的判题机信息
        return res.get(0);
    }
}
