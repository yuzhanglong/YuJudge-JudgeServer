package com.yzl.yujudge;

import com.yzl.yujudge.core.task.UserTask;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.repository.SubmissionRepository;
import com.yzl.yujudge.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class ScheduleTaskTest {

    @Autowired
    private UserTask userTask;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Test
    void testSyncUserAcAmountAndSubmissionAmount() {
        userTask.syncUserAcAmountAndSubmissionAmount();
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity user : users) {
            Long acAmount = submissionRepository.countAcAmountByCreator(user);
            Long submissionAmount = submissionRepository.countSubmissionAmountByCreator(user);
            assertThat(user.getAcAmount().toString()).isEqualTo(acAmount.toString());
            assertThat(user.getSubmissionAmount().toString()).isEqualTo(submissionAmount.toString());
        }
    }
}
