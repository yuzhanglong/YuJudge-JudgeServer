package com.yzl.yujudge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.bo.JudgeHostBO;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.JudgeHostConditionDTO;
import com.yzl.yujudge.dto.JudgeHostConnectionDTO;
import com.yzl.yujudge.dto.JudgeHostDTO;
import com.yzl.yujudge.model.JudgeHostEntity;
import com.yzl.yujudge.network.JudgeHostCommonRequest;
import com.yzl.yujudge.repository.JudgeHostRepository;
import com.yzl.yujudge.repository.SubmissionRepository;
import com.yzl.yujudge.store.redis.JudgeHostCache;
import com.yzl.yujudge.utils.comparator.BestJudgeHostComparator;
import com.yzl.yujudge.vo.CountSubmissionByTimeVO;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yuzhanglong
 * @description 判题服务器的相关业务
 * @date 2020-7-30 19:00
 */

@Service
public class JudgeHostService {
    private final JudgeHostRepository judgeHostRepository;
    private final JudgeHostCache judgeHostCache;
    private final Mapper mapper;
    private final ObjectMapper objectMapper;
    private final SubmissionRepository submissionRepository;
    private final ProblemSetService problemSetService;

    public JudgeHostService(
            JudgeHostRepository judgeHostRepository,
            JudgeHostCache judgeHostCache,
            Mapper mapper, ObjectMapper objectMapper,
            SubmissionRepository submissionRepository,
            ProblemSetService problemSetService) {
        this.judgeHostRepository = judgeHostRepository;
        this.judgeHostCache = judgeHostCache;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
        this.submissionRepository = submissionRepository;
        this.problemSetService = problemSetService;
    }

    /**
     * @author yuzhanglong
     * @description 获取所有判题服务器信息
     * @date 2020-7-30 19:06
     */
    public List<JudgeHostBO> inspectJudgeHosts(List<JudgeHostEntity> judgeHostEntityList) {
        List<JudgeHostBO> judgeHostBOList = new ArrayList<>();
        for (JudgeHostEntity res : judgeHostEntityList) {
            JudgeHostBO judgeHost = mapper.map(res, JudgeHostBO.class);
            String address = res.getAddress();
            JudgeHostCommonRequest request = new JudgeHostCommonRequest(address);
            try {
                String testResponse = request.testJudgeConnection();
                JudgeHostConnectionDTO responseDTO = objectMapper.readValue(testResponse, JudgeHostConnectionDTO.class);
                boolean isSuccess = isConnectSuccess(responseDTO);
                judgeHost.setCondition(responseDTO.getData());
                judgeHost.setConnection(isSuccess);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                // 此处为连接错误(响应超时等)
                judgeHost.setCondition(new JudgeHostConditionDTO());
                judgeHost.setConnection(false);
            }
            judgeHostBOList.add(judgeHost);
        }
        return judgeHostBOList;
    }

    /**
     * @param judgeHostDTO 判题机相关数据传输对象
     * @author yuzhanglong
     * @description 添加一个judgeHost记录
     * @date 2020-7-30 18:34
     */
    public void createJudgeHost(JudgeHostDTO judgeHostDTO) {
        JudgeHostEntity judgeHostEntity = mapper.map(judgeHostDTO, JudgeHostEntity.class);
        judgeHostRepository.save(judgeHostEntity);
    }


    /**
     * @param connectionDTO judgeHost response数据传输对象
     * @return Boolean 是否连接正常
     * @author yuzhanglong
     * @description 判断是否连接正常
     * @date 2020-7-30 23:53
     */
    private Boolean isConnectSuccess(JudgeHostConnectionDTO connectionDTO) {
        String code = connectionDTO.getCode();
        return "00000".equals(code);
    }

    /**
     * @author yuzhanglong
     * @description 判题机状态的信息, 存入redis中
     * @date 2020-08-17 14:18:59
     */
    public void setJudgeConditionCache() {
        // 从数据库中找出所有判题机信息
        List<JudgeHostEntity> judgeHostEntities = judgeHostRepository.findAll();
        List<JudgeHostBO> judgeHostBOList = inspectJudgeHosts(judgeHostEntities);
        judgeHostCache.setJudgeConditionCache(judgeHostBOList);
    }

    /**
     * @return List<JudgeHostBO> 判题机相关业务对象
     * @author yuzhanglong
     * @description 获取缓存中判题机状态的信息
     * @date 2020-08-17 14:24:55
     */
    public List<JudgeHostBO> getJudgeHostsCondition() {
        List<Object> rawList = judgeHostCache.getJudgeHostsConditionListCache();
        List<JudgeHostBO> judgeHostBOList = new ArrayList<>();
        for (Object o : rawList) {
            judgeHostBOList.add(mapper.map(o, JudgeHostBO.class));
        }
        return judgeHostBOList;
    }

    public JudgeHostBO getJudgeHostConditionById(Long judgeHostId) {
        Object judgeHost = judgeHostCache.getJudgeHostsConditionByJudgeHostId(judgeHostId.toString());
        if (judgeHost == null) {
            throw new NotFoundException("B0013");
        }
        return mapper.map(judgeHost, JudgeHostBO.class);
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
        List<JudgeHostBO> judgeHosts = getJudgeHostsCondition();
        // TODO: 设计一个负载均衡的算法，最终返回一个可以请求的判题机信息
        int finalIndex = -1;
        int judgeHostAmount = judgeHosts.size();
        judgeHosts.sort(new BestJudgeHostComparator());

        for (int i = 0; i < judgeHostAmount; i++) {
            JudgeHostBO judgeHost = judgeHosts.get(i);
            // isActive 由系统管理员控制，表示判题机是否开启
            boolean isActive = judgeHost.getActive();
            // isConnected 由服务端定时请求判题机得来，如果为真表示连接正常
            boolean isConnected = judgeHost.getConnection();
            if (isActive && isConnected) {
                finalIndex = i;
                break;
            }
        }
        if (finalIndex == -1) {
            return null;
        }
        return judgeHosts.get(finalIndex);
    }


    /**
     * @return boolean 当前该判题机状态
     * @author yuzhanglong
     * @date 2020-8-18 23:46:27
     * @description 改变某个判题服务器活跃状态【isActive】
     * 如果当前为【活跃】状态，将被替换为【非活跃状态】，反之亦然
     * 值得注意的是, 改变活跃状态并不是停止判题服务器的服务
     * 但是，接下来执行判题操作时，
     * 我们不会再请求【非活跃】的服务器，直至状态被重置为【活跃】
     */
    public Boolean makeJudgeHostActiveOrUnActive(Long judgeHostId) {
        JudgeHostEntity judgeHostEntity = judgeHostRepository.findOneById(judgeHostId);
        if (judgeHostEntity == null) {
            throw new NotFoundException("B0013");
        }
        boolean isActive = judgeHostEntity.getActive();
        judgeHostEntity.setActive(!isActive);
        judgeHostRepository.save(judgeHostEntity);
        // 更新缓存
        setJudgeConditionCache();
        return !isActive;
    }

    /**
     * @param judgeHostId 判题机id
     * @param begin       开始时间
     * @param end         结束时间
     * @author yuzhanglong
     * @description 查询、序列化某个时间段内某个判题机的提交数据
     * @date 2020-8-19 17:09:33
     */
    public CountSubmissionByTimeVO countJudgeHostsSubmissionByTimeBetween(Date begin, Date end, Long judgeHostId) {
        // 查询结果
        Set<List<Object>> results = submissionRepository.countSubmissionGroupByHoursByJudgeHostId(begin, end, judgeHostId);
        List<Map<String, Object>> items = problemSetService.publishSubmissionHourlyCount(results);
        return new CountSubmissionByTimeVO((long) results.size(), items);
    }
}
