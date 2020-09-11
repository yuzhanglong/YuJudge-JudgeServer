package com.yzl.yujudge.controller.v1;

import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.bo.JudgeHostBO;
import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.enumeration.PermissionEnum;
import com.yzl.yujudge.dto.JudgeHostDTO;
import com.yzl.yujudge.dto.SetWorkingAmountDTO;
import com.yzl.yujudge.service.JudgeHostService;
import com.yzl.yujudge.utils.DateTimeUtil;
import com.yzl.yujudge.vo.CountSubmissionByTimeVO;
import com.yzl.yujudge.vo.JudgeHostVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 判题服务器相关控制层
 *
 * @author yuzhanglong
 * @date 2020-7-30
 */

@RestController
@Validated
@RequestMapping("/judge_host")
public class JudgeHostController {
    private final JudgeHostService judgeHostService;
    private final Mapper mapper;

    public JudgeHostController(JudgeHostService judgeHostService, Mapper mapper) {
        this.judgeHostService = judgeHostService;
        this.mapper = mapper;
    }

    /**
     * 添加一个judgeHost记录
     *
     * @param judgeHostDTO 判题机信息数据传输对象
     * @author yuzhanglong
     * @date 2020-8-21 22:18:37
     */
    @PutMapping("/create_judge_host")
    @AuthorizationRequired(permission = PermissionEnum.ADMIN)
    public UnifiedResponse createJudgeHost(@RequestBody @Validated JudgeHostDTO judgeHostDTO) {
        judgeHostService.createJudgeHost(judgeHostDTO);
        return new UnifiedResponse("添加判题服务器信息成功");
    }

    /**
     * 获取当前所有判题服务器信息
     *
     * @author yuzhanglong
     * @date 2020-08-16 21:02:54
     */
    @GetMapping("/get_judge_hosts_info")
    @AuthorizationRequired
    public UnifiedResponse getJudgeHostsInfo() {
        List<JudgeHostBO> judgeHostVOList = judgeHostService.getJudgeHostsCondition();
        return new UnifiedResponse(judgeHostVOList);
    }

    /**
     * 获取判题服务器信息
     *
     * @param judgeHostId 判题机id
     * @author yuzhanglong
     * @date 2020-08-16 21:02:54
     */
    @GetMapping("/get_judge_host_by_id/{judgeHostId}")
    @AuthorizationRequired
    public UnifiedResponse getJudgeHostById(@PathVariable Long judgeHostId) {
        JudgeHostBO judgeHostBO = judgeHostService.getJudgeHostConditionById(judgeHostId);
        return new UnifiedResponse(mapper.map(judgeHostBO, JudgeHostVO.class));
    }

    /**
     * 修改判题机状态，
     * 相关解释请参考 makeJudgeHostActiveOrUnActive方法
     *
     * @param judgeHostId 判题机id
     * @author yuzhanglong
     * @date 2020-8-19 00:18:05
     */
    @PutMapping("/reset_active_condition/{judgeHostId}")
    @AuthorizationRequired
    public UnifiedResponse makeJudgeHostActiveOrUnActive(@PathVariable Long judgeHostId) {
        Boolean isActive = judgeHostService.makeJudgeHostActiveOrUnActive(judgeHostId);
        return new UnifiedResponse("状态修改成功，当前状态: " + (isActive ? "运行中" : "已暂停"));
    }


    /**
     * 统计某个时间段内某个判题机的提交数据
     *
     * @param judgeHostId 判题机id
     * @param begin       开始时间
     * @param end         结束时间
     * @author yuzhanglong
     * @date 2020-8-19 17:04:47
     */
    @GetMapping("/count_judge_host_submission")
    @AuthorizationRequired
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


    /**
     * 设置判题机工作节点
     *
     * @param judgeHostId         判题机id
     * @param setWorkingAmountDTO 设置判题机节点的数据传输对象
     * @author yuzhanglong
     * @date 2020-8-29 18:50:38
     */
    @PutMapping("/set_judge_host_working_amount/{judgeHostId}")
    @AuthorizationRequired(permission = PermissionEnum.ADMIN)
    @Deprecated
    public UnifiedResponse setJudgeHostServiceWorkingAmount(
            @PathVariable Long judgeHostId,
            @Validated @RequestBody SetWorkingAmountDTO setWorkingAmountDTO) {
        judgeHostService.setJudgeHostServiceWorkingAmount(judgeHostId, setWorkingAmountDTO);
        return new UnifiedResponse("设置成功~");
    }

    /**
     * 移除一个判题服务器
     *
     * @param judgeHostId 判题机id
     * @author yuzhanglong
     * @date 2020-8-31 16:08:09
     */
    @DeleteMapping("/delete_judge_host/{judgeHostId}")
    @AuthorizationRequired(permission = PermissionEnum.ADMIN)
    public UnifiedResponse deleteJudgeHost(@PathVariable Long judgeHostId) {
        judgeHostService.deleteJudgeHost(judgeHostId);
        return new UnifiedResponse("设置成功~");
    }

    /**
     * 重定向到判题服务器下载提交
     *
     * @author yuzhanglong
     * @date 2020-9-11 12:09:48
     */
    @GetMapping("/submission")
    public void downloadSubmission(
            @RequestParam Long host,
            @RequestParam String submission,
            HttpServletResponse response) throws IOException {
        String urlInJudgeHost = judgeHostService.downloadSubmission(host, submission);
        response.sendRedirect(urlInJudgeHost);
    }
}