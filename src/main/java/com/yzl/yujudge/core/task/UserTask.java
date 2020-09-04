package com.yzl.yujudge.core.task;

import com.yzl.yujudge.core.enumeration.JudgeResultEnum;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.repository.SubmissionRepository;
import com.yzl.yujudge.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 用户信息定时任务
 *
 * @author yuzhanglong
 * @date 2020-9-4 22:16:15
 */

@Component
public class UserTask {
    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;

    public UserTask(UserRepository userRepository, SubmissionRepository submissionRepository) {
        this.userRepository = userRepository;
        this.submissionRepository = submissionRepository;
    }

    /**
     * 定时更新用户的ac数目和提交数目
     *
     * @author yuzhanglong
     * @date 2020-9-4 22:21:22
     */
    @Scheduled(cron = "0 0 0,6,12,18 * * ?")
    public void syncUserAcAmountAndSubmissionAmount() {
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity userEntity : userEntities) {
            Set<List<Object>> counts = submissionRepository.countUserTotalSubmissionCountDate(userEntity.getId());
            int cnt = 0;
            for (List<Object> count : counts) {
                if (count.get(0).toString().equals(JudgeResultEnum.ACCEPT.toString())) {
                    userEntity.setAcAmount(Integer.parseInt(count.get(1).toString()));
                }
                cnt += Integer.parseInt(count.get(1).toString());
            }
            userEntity.setSubmissionAmount(cnt);
            userRepository.save(userEntity);
        }
    }
}
