package com.yzl.yujudge.service;

import com.qiniu.util.Auth;
import com.yzl.yujudge.core.configuration.UploadConfiguration;
import com.yzl.yujudge.model.DailyWordEntity;
import com.yzl.yujudge.repository.*;
import com.yzl.yujudge.utils.DateTimeUtil;
import com.yzl.yujudge.utils.EntityToVoMapper;
import com.yzl.yujudge.vo.DailyWordVO;
import com.yzl.yujudge.vo.GlobalCountVO;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 一般性业务逻辑
 *
 * @author yuzhanglong
 * @date 2020-08-05 20:44:04
 */

@Service
public class CommonService {
    private final UploadConfiguration uploadConfiguration;
    private final SubmissionRepository submissionRepository;
    private final ProblemSetRepository problemSetRepository;
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;
    private final JudgeHostRepository judgeHostRepository;
    private final DailyWordRepository dailyWordRepository;

    public CommonService(
            UploadConfiguration uploadConfiguration,
            SubmissionRepository submissionRepository,
            ProblemSetRepository problemSetRepository,
            ProblemRepository problemRepository,
            UserRepository userRepository,
            JudgeHostRepository judgeHostRepository,
            DailyWordRepository dailyWordRepository) {
        this.uploadConfiguration = uploadConfiguration;
        this.submissionRepository = submissionRepository;
        this.problemSetRepository = problemSetRepository;
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
        this.judgeHostRepository = judgeHostRepository;
        this.dailyWordRepository = dailyWordRepository;
    }

    /**
     * 获取上传凭证
     *
     * @author yuzhanglong
     * @date 2020-08-06 12:47:19
     */
    public String getUploadToken() {
        String accessKey = uploadConfiguration.getAccessKey();
        String secretKey = uploadConfiguration.getSecretKey();
        String bucketName = uploadConfiguration.getBucket();
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucketName);
    }

    /**
     * 获取全局统计信息
     *
     * @author yuzhanglong
     * @date 2020-08-06 12:47:19
     */
    public GlobalCountVO getGlobalCount() {
        // 现在
        Date now = new Date();
        // 今天的开始
        Date toDayBegin = DateTimeUtil.removeTimeFromDateObject(now);
        // 今天的结束
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        c1.add(Calendar.DATE, 1);
        Date toDayEnd = DateTimeUtil.removeTimeFromDateObject(DateTimeUtil.removeTimeFromDateObject(c1.getTime()));
        List<Map<String, Object>>  countGlobalSubmission = countGlobalSubmission(toDayBegin, toDayEnd);
        return new GlobalCountVO(
                problemRepository.count(),
                problemSetRepository.count(),
                submissionRepository.count(),
                userRepository.count(),
                judgeHostRepository.count(),
                countGlobalSubmission
        );
    }

    /**
     * 全局提交计数
     *
     * @return 计数结果数组
     * @author yuzhanglong
     * @date 2020-8-24 19:47:17
     */
    public List<Map<String, Object>> countGlobalSubmission(Date start, Date end) {
        Set<List<Object>> res = submissionRepository.countSubmissionGroupByHours(start, end);
        return SubmissionService.publishSubmissionHourlyCount(res);
    }

    /**
     * 获取每日一句
     *
     * @return 计数结果数组
     * @author yuzhanglong
     * @date 2020-8-30 21:18:40
     */
    public DailyWordVO getDailyWord(){
        Calendar calendar = Calendar.getInstance();
        Long dayOfYear = (long) calendar.get(Calendar.DAY_OF_YEAR);
        DailyWordEntity dailyWordEntity = dailyWordRepository.findOneById(dayOfYear);
        EntityToVoMapper<DailyWordEntity, DailyWordVO> mapper = new EntityToVoMapper<>(dailyWordEntity, DailyWordVO.class);
        return mapper.getViewObject();
    }
}