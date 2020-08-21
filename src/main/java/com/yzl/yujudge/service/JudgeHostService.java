package com.yzl.yujudge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.bo.JudgeHostBO;
import com.yzl.yujudge.core.exception.http.ForbiddenException;
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
import com.yzl.yujudge.utils.validators.PublicValidator;
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
            JudgeHostCommonRequest request = new JudgeHostCommonRequest(res.getBaseUrl(), res.getPort());
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
     * 添加一个judgeHost
     *
     * @param judgeHostDTO 判题机相关数据传输对象
     * @author yuzhanglong
     * @date 2020-7-30 18:34
     */
    public void createJudgeHost(JudgeHostDTO judgeHostDTO) {
        String baseUrl = judgeHostDTO.getBaseUrl();
        Integer port = judgeHostDTO.getPort();
        // IP 合法性
        if (!PublicValidator.isAddressValidated(baseUrl)) {
            throw new ForbiddenException("A0009");
        }
        // 端口合法性
        if (!PublicValidator.isPortValidated(port)) {
            throw new ForbiddenException("A0010");
        }
        // IP地址是否重复
        if (judgeHostRepository.findOneByBaseUrl(baseUrl) != null) {
            throw new ForbiddenException("B0014");
        }
        JudgeHostEntity judgeHostEntity = mapper.map(judgeHostDTO, JudgeHostEntity.class);
        judgeHostEntity.setBaseUrl(baseUrl);
        // 活跃状态默认置为否
        judgeHostEntity.setActive(false);
        judgeHostRepository.save(judgeHostEntity);
    }


    /**
     * 判断是否连接正常
     *
     * @param connectionDTO judgeHost response数据传输对象
     * @return Boolean 是否连接正常
     * @author yuzhanglong
     * @date 2020-7-30 23:53
     */
    private Boolean isConnectSuccess(JudgeHostConnectionDTO connectionDTO) {
        String code = connectionDTO.getCode();
        return "00000".equals(code);
    }

    /**
     * 将判题机状态的信息,存入redis中
     *
     * @author yuzhanglong
     * @date 2020-08-17 14:18:59
     */
    public void setJudgeConditionCache() {
        // 从数据库中找出所有判题机信息
        List<JudgeHostEntity> judgeHostEntities = judgeHostRepository.findAll();
        List<JudgeHostBO> judgeHostBOList = inspectJudgeHosts(judgeHostEntities);
        judgeHostCache.setJudgeConditionCache(judgeHostBOList);
    }

    /**
     * 获取缓存中判题机状态的信息
     *
     * @return 判题机相关业务对象
     * @author yuzhanglong
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

    /**
     * 获取缓存中判题机状态的信息(限制了判题机id)
     *
     * @return 判题机相关业务对象
     * @author yuzhanglong
     * @date 2020-08-17 14:24:55
     */
    public JudgeHostBO getJudgeHostConditionById(Long judgeHostId) {
        Object judgeHost = judgeHostCache.getJudgeHostsConditionByJudgeHostId(judgeHostId.toString());
        if (judgeHost == null) {
            throw new NotFoundException("B0013");
        }
        return mapper.map(judgeHost, JudgeHostBO.class);
    }


    /**
     * 选择一个合适的判题机以发送提交请求
     * 当前我们有多个判题机集群部署, 当前缓存中已经有了所有判题机的信息
     * 我们需要达到的目标
     * 1. 保证程序的稳定性
     * 2. 维持负载均衡
     *
     * @return 可以请求的judgeHostBO
     * @author yuzhanglong
     * @date 2020-08-17 15:23:23
     */
    public JudgeHostBO chooseJudgeHostToRequest() {
        // 从缓存中获取所有判题机的信息
        List<JudgeHostBO> judgeHosts = getJudgeHostsCondition();
        int finalIndex = -1;
        // 及时移除没有正常连接的judgeHost，
        // 因为这种类型的judgeHost没有有效的 JudgeHostConditionDTO 对象，
        // 即getCondition会返回null，最终导致sort时爆NPE错误
        judgeHosts.removeIf(judgeHost -> !judgeHost.getConnection());

        // 排序，以获取最优判题机
        judgeHosts.sort(new BestJudgeHostComparator());

        for (int i = 0; i < judgeHosts.size(); i++) {
            JudgeHostBO judgeHost = judgeHosts.get(i);
            // isActive 由系统管理员控制，表示判题机是否开启
            boolean isActive = judgeHost.getActive();
            if (isActive) {
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
     * 改变某个判题服务器活跃状态【isActive】
     * 如果当前为【活跃】状态，将被替换为【非活跃状态】，反之亦然
     * 值得注意的是, 改变活跃状态并不是停止判题服务器的服务
     * 但是，接下来执行判题操作时，
     * 我们不会再请求【非活跃】的服务器，直至状态被重置为【活跃】
     *
     * @return boolean 当前该判题机状态
     * @author yuzhanglong
     * @date 2020-8-18 23:46:27
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
     * 查询、序列化某个时间段内某个判题机的提交数据
     *
     * @param judgeHostId 判题机id
     * @param begin       开始时间
     * @param end         结束时间
     * @author yuzhanglong
     * @date 2020-8-19 17:09:33
     */
    public CountSubmissionByTimeVO countJudgeHostsSubmissionByTimeBetween(Date begin, Date end, Long judgeHostId) {
        // 查询结果
        Set<List<Object>> results = submissionRepository.countSubmissionGroupByHoursByJudgeHostId(begin, end, judgeHostId);
        List<Map<String, Object>> items = problemSetService.publishSubmissionHourlyCount(results);
        return new CountSubmissionByTimeVO((long) results.size(), items);
    }
}
