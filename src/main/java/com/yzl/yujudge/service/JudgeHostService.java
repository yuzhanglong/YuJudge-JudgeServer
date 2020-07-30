package com.yzl.yujudge.service;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.dto.JudgeHostDTO;
import com.yzl.yujudge.model.JudgeHostEntity;
import com.yzl.yujudge.repository.JudgeHostRepository;
import org.springframework.stereotype.Service;

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
    public List<JudgeHostEntity> inspectJudgeHosts() {
        return judgeHostRepository.findAll();
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
}
