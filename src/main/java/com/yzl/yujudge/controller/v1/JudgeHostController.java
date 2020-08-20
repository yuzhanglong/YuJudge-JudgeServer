package com.yzl.yujudge.controller.v1;

import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.bo.JudgeHostBO;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.dto.JudgeHostDTO;
import com.yzl.yujudge.service.JudgeHostService;
import com.yzl.yujudge.utils.DateTimeUtil;
import com.yzl.yujudge.vo.CountSubmissionByTimeVO;
import com.yzl.yujudge.vo.JudgeHostVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuzhanglong
 * @description 判题服务器相关控制层
 * @date 2020-7-30
 */

@RestController
@Validated
@CrossOrigin
@RequestMapping("/judge_host")
public class JudgeHostController {
    private final JudgeHostService judgeHostService;
    private final Mapper mapper;

    public JudgeHostController(JudgeHostService judgeHostService, Mapper mapper) {
        this.judgeHostService = judgeHostService;
        this.mapper = mapper;
    }

    /**
     * @param judgeHostDTO 判题机信息数据传输对象
     * @author yuzhanglong
     * @description 添加一个judgeHost记录
     * @date 2020-7-30 18:31
     */
    @PostMapping("/create")
    public UnifiedResponse createJudgeHost(@RequestBody @Validated JudgeHostDTO judgeHostDTO) {
        judgeHostService.createJudgeHost(judgeHostDTO);
        return new UnifiedResponse("添加判题服务器信息成功");
    }

    /**
     * @author yuzhanglong
     * @description 获取当前所有判题服务器信息
     * @date 2020-08-16 21:02:54
     */
    @GetMapping("/get_judge_hosts_info")
    public UnifiedResponse getJudgeHostsInfo() {
        List<JudgeHostBO> judgeHostVOList = judgeHostService.getJudgeHostsCondition();
        return new UnifiedResponse(judgeHostVOList);
    }

    /**
     * @param judgeHostId 判题机id
     * @author yuzhanglong
     * @description 获取当前所有判题服务器信息
     * @date 2020-08-16 21:02:54
     */
    @GetMapping("/get_judge_host_by_id/{judgeHostId}")
    public UnifiedResponse getJudgeHostById(@PathVariable Long judgeHostId) {
        JudgeHostBO judgeHostBO = judgeHostService.getJudgeHostConditionById(judgeHostId);
        return new UnifiedResponse(mapper.map(judgeHostBO, JudgeHostVO.class));
    }

    /**
     * @param judgeHostId 判题机id
     * @author yuzhanglong
     * @description 修改判题机状态，
     * 相关解释请参考 makeJudgeHostActiveOrUnActive方法
     * @date 2020-8-19 00:18:05
     */
    @PutMapping("/reset_active_condition/{judgeHostId}")
    public UnifiedResponse makeJudgeHostActiveOrUnActive(@PathVariable Long judgeHostId) {
        Boolean isActive = judgeHostService.makeJudgeHostActiveOrUnActive(judgeHostId);
        return new UnifiedResponse("状态修改成功，当前状态: " + (isActive ? "运行中" : "已暂停"));
    }


    /**
     * @param judgeHostId 判题机id
     * @param begin       开始时间
     * @param end         结束时间
     * @author yuzhanglong
     * @description 统计某个时间段内某个判题机的提交数据
     * @date 2020-8-19 17:04:47
     */
    @GetMapping("/count_judge_host_submission")
    public UnifiedResponse countJudgeHostSubmission(
            @RequestParam String begin,
            @RequestParam String end,
            @RequestParam Long judgeHostId) {
            CountSubmissionByTimeVO res = judgeHostService.countJudgeHostsSubmissionByTimeBetween(
                    DateTimeUtil.formatDateTimeString(begin),
                    DateTimeUtil.formatDateTimeString(end),
                    judgeHostId
            );
            return new UnifiedResponse(res);
    }
}