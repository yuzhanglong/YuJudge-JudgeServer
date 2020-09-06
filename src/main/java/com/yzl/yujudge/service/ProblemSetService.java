package com.yzl.yujudge.service;


import com.github.dozermapper.core.Mapper;
import com.yzl.yujudge.bo.ScoreBoardBO;
import com.yzl.yujudge.bo.ScoreBoardItemBO;
import com.yzl.yujudge.bo.TeamProblemsSolutionBO;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.enumeration.LanguageEnum;
import com.yzl.yujudge.core.enumeration.ProblemSetConditionEnum;
import com.yzl.yujudge.core.exception.http.ForbiddenException;
import com.yzl.yujudge.core.exception.http.NotFoundException;
import com.yzl.yujudge.dto.ProblemSetDTO;
import com.yzl.yujudge.model.JudgeProblemEntity;
import com.yzl.yujudge.model.ProblemSetEntity;
import com.yzl.yujudge.model.SubmissionEntity;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.repository.ProblemRepository;
import com.yzl.yujudge.repository.ProblemSetRepository;
import com.yzl.yujudge.repository.SubmissionRepository;
import com.yzl.yujudge.repository.UserRepository;
import com.yzl.yujudge.store.redis.ProblemSetCache;
import com.yzl.yujudge.utils.DateTimeUtil;
import com.yzl.yujudge.utils.comparator.ScoreBoardItemComparator;
import com.yzl.yujudge.vo.CountSubmissionByTimeVO;
import com.yzl.yujudge.vo.UserInfoBasicVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * problemSet的业务逻辑层
 *
 * @author yuzhanglong
 * @date 2020-08-08 21:59:56
 */
@Service
public class ProblemSetService {
    private final ProblemSetRepository problemSetRepository;
    private final UserRepository userRepository;
    private final UserGroupService userGroupService;
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;
    private final ProblemSetCache problemSetCache;
    private final Mapper mapper;
    private final Pageable findFirstPageable = PageRequest.of(0, 1);


    public ProblemSetService(
            ProblemSetRepository problemSetRepository,
            UserRepository userRepository,
            UserGroupService userGroupService, ProblemRepository problemRepository,
            SubmissionRepository submissionRepository,
            ProblemSetCache problemSetCache,
            Mapper mapper) {
        this.problemSetRepository = problemSetRepository;
        this.userRepository = userRepository;
        this.userGroupService = userGroupService;
        this.problemRepository = problemRepository;
        this.submissionRepository = submissionRepository;
        this.problemSetCache = problemSetCache;
        this.mapper = mapper;
    }

    /**
     * 获取题目集相关信息
     *
     * @param isBeforeDeadline 限定截止时间，只选出活跃的题目集合
     * @param count            数量
     * @param start            页码
     * @param search           关键词限制(搜索)
     * @return ProblemSetEntity 的分页对象
     * @author yuzhanglong
     * @date 2020-7-29 00:30:19
     */
    public Page<ProblemSetEntity> getProblemSetInfo(
            Integer start,
            Integer count,
            String search,
            Boolean isBeforeDeadline) {
        Pageable pageable = PageRequest.of(start, count);
        if (!isBeforeDeadline) {
            return problemSetRepository.findByName(search, pageable);
        } else {
            Date current = new Date();
            return problemSetRepository.findByNameBetweenCurrentTime(current, search, pageable);
        }
    }

    /**
     * 根据ID 获取题目集
     *
     * @param problemSetId 题目集id
     * @param start        页码
     * @param count        每页的个数
     * @return ProblemSetEntity 实体对象
     * @author yuzhanglong
     * @date 2020-08-10 18:49:35
     */
    public Page<JudgeProblemEntity> getProblemSetProblems(
            Integer start,
            Integer count,
            Long problemSetId) {
        Pageable pageable = PageRequest.of(start, count);
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        // 当传入的题目集为空时，我们进行全局题目的查询
        if (problemSetId == null) {
            return problemRepository.findAll(pageable);
        }
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        return problemRepository.findAllByProblemSetEntityList(problemSetEntity, pageable);
    }


    /**
     * 创建一个题目集
     *
     * @param problemSetDTO 题目集的数据传输对象
     * @author yuzhanglong
     * @date 2020-08-09 15:51:18
     */
    public void createProblemSet(ProblemSetDTO problemSetDTO) {
        ProblemSetEntity problemSetEntity = mapper.map(problemSetDTO, ProblemSetEntity.class);
        UserEntity user = userRepository.findOneById(UserHolder.getUserId());
        if (user == null) {
            throw new NotFoundException("A0001");
        }
        problemSetEntity.setCreator(user);
        problemSetEntity.setAllowedLanguage(LanguageEnum.getAllAcceptedLanguage());
        problemSetRepository.save(problemSetEntity);
    }


    /**
     * 更新题目集的测试点(解决方案)
     *
     * @param problemSetId 目标题目集id
     * @param problems     将要被加入进题目集的problem数组(id的形式)
     * @author yuzhanglong
     * @date 2020-08-10 23:11:27
     */
    public void updateProblemSetProblem(Long problemSetId, List<Long> problems) {
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        List<Long> ps = cutProblemIdsList(problems, problemSetEntity.getProblems());
        List<JudgeProblemEntity> problemEntityList = problemRepository.findAllById(ps);
        problemSetEntity.getProblems().addAll(problemEntityList);
        problemSetRepository.save(problemSetEntity);
    }


    /**
     * 题目id数组去重，防止重复添加
     *
     * @param problems           用户传入的题目id数组
     * @param problemSetProblems 实际题目集中含有的problems
     * @return List<Long> 去重之后的数组
     * @author yuzhanglong
     * @date 2020-08-10 23:09:36
     */
    private List<Long> cutProblemIdsList(List<Long> problems, List<JudgeProblemEntity> problemSetProblems) {
        problemSetProblems.forEach(res -> {
            Long isContainProblemId = res.getId();
            problems.remove(isContainProblemId);
        });
        return problems;
    }


    /**
     * 从题目集中移除某个问题(不删除问题)
     * 如果这个题目集中没有这个问题，
     * 则数据库内容不会发生改变，
     * 并且我们会抛出一个全局异常 编号为 B0012
     *
     * @param problemId    要从题目集中移除的问题id
     * @param problemSetId 操作的题目集id
     * @author yuzhanglong
     * @date 2020-08-10 23:06:09
     */
    public void removeProblemFromProblemSet(Long problemSetId, Long problemId) {
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        JudgeProblemEntity problemEntity = problemRepository.findOneById(problemId);
        if (problemEntity == null) {
            throw new NotFoundException("B0002");
        }
        int index = getProblemSetProblemIndex(problemSetEntity.getProblems(), problemId);
        if (index == -1) {
            // 走到这里说明这个题目集中没有这个问题
            throw new NotFoundException("B0012");
        }
        problemSetEntity.getProblems().remove(index);
        problemSetRepository.save(problemSetEntity);
    }

    /**
     * 获取问题在题目集的索引
     *
     * @param problemEntityList 问题实体类
     * @param problemId         操作的题目id
     * @author yuzhanglong
     * @date 2020-9-6 14:24:41
     */
    public Integer getProblemSetProblemIndex(List<JudgeProblemEntity> problemEntityList, Long problemId) {
        for (int i = 0; i < problemEntityList.size(); i++) {
            if (problemEntityList.get(i).getId().equals(problemId)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 通过题目集id来获取某个题目集信息, 附带开放性验证、存在性验证
     *
     * @param problemSetId 操作的题目集id
     * @author yuzhanglong
     * @date 2020-9-6 11:07:46
     */
    public ProblemSetEntity validateAndGetProblemSetById(Long problemSetId) {
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        Long uid = UserHolder.getUserId();
        // 题目集非公开, 且用户不在规定的参与者中
        if (!problemSetEntity.getOpen() && !isUserInProblemSetParticipant(problemSetEntity, uid)) {
            throw new ForbiddenException("B0022");
        }
        return problemSetEntity;
    }

    /**
     * 检查用户是否在题目集参与者中
     *
     * @param problemSetEntity 题目集实体类
     * @param userId           用户id
     * @author yuzhanglong
     * @date 2020-08-12 12:23:53
     */
    private Boolean isUserInProblemSetParticipant(ProblemSetEntity problemSetEntity, Long userId) {
        // 系统默认的无限制用户组
        if (userGroupService.isUserProblemSetFree()) {
            return true;
        }
        List<UserEntity> participants = problemSetEntity.getParticipants();
        for (UserEntity participant : participants) {
            if (participant.getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取题目集的状态
     *
     * @param problemSetEntity 题目集实体类
     * @author yuzhanglong
     * @description ProblemSetConditionEnum 状态枚举类
     * @date 2020-08-12 10:10:24
     */
    public ProblemSetConditionEnum getProblemSetCondition(ProblemSetEntity problemSetEntity) {
        Date startTime = problemSetEntity.getStartTime();
        Date deadline = problemSetEntity.getDeadline();
        Integer timeCondition = DateTimeUtil.checkTimeCondition(startTime, deadline);
        if (timeCondition == DateTimeUtil.TOO_LATE) {
            return ProblemSetConditionEnum.CLOSED;
        }
        if (timeCondition == DateTimeUtil.TOO_EARLY) {
            return ProblemSetConditionEnum.NOT_STARTED;
        }
        return ProblemSetConditionEnum.RUNNING;
    }


    /**
     * 获取题目集计分板信息
     *
     * @param problemSetEntity 题目集
     * @author yuzhanglong
     * @description ScoreBoardBO 记分板相关业务对象
     * @date 2020-08-13 17:44:56
     */
    public ScoreBoardBO getProblemSetScoreBoard(ProblemSetEntity problemSetEntity) {
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        List<JudgeProblemEntity> judgeProblemEntityList = problemSetEntity.getProblems();
        List<UserEntity> participants = problemSetEntity.getParticipants();
        // 遍历参与者
        List<ScoreBoardItemBO> scoreBoardItemBOList = new ArrayList<>();
        for (int i = 0; i < participants.size(); i++) {
            UserEntity user = participants.get(i);
            ScoreBoardItemBO item = new ScoreBoardItemBO();
            item.setTeamInfo(mapper.map(user, UserInfoBasicVO.class));
            TeamProblemsSolutionBO singleProblemSolutionInfo = countTeamProblemSolutionInfo(
                    user,
                    problemSetEntity,
                    judgeProblemEntityList
            );
            item.setSolutionInfo(singleProblemSolutionInfo.getProblemResult());
            item.setRank(i + 1);
            item.setTotalAcAmount(singleProblemSolutionInfo.getTotalAcAmount());
            item.setTotalTimePenalty(singleProblemSolutionInfo.getTotalTimePenalty());
            scoreBoardItemBOList.add(item);
        }
        scoreBoardItemBOList.sort(new ScoreBoardItemComparator());
        return new ScoreBoardBO(false, scoreBoardItemBOList, judgeProblemEntityList.size());
    }

    /**
     * 获取题目集中一个团队的做题信息
     * 这些信息包括：总罚时、各个题目的尝试次数、是否ac/一血等信息
     *
     * @param user       用户/队伍
     * @param problemSet 题目集实体对象
     * @param problems   题目集所有的问题
     * @author yuzhanglong
     * @date 2020-08-13 22:50:28
     */
    private TeamProblemsSolutionBO countTeamProblemSolutionInfo(UserEntity user, ProblemSetEntity problemSet, List<JudgeProblemEntity> problems) {
        List<Map<String, Object>> problemList = new ArrayList<>(3);
        // 总的ac个数
        int totalAcAmount = 0;
        // 罚时
        long totalTimePenalty = 0;
        for (JudgeProblemEntity problemEntity : problems) {
            // 拿到这支队伍在本题第一次的ac
            List<SubmissionEntity> firstAcSubmission = submissionRepository.findUserProblemAcInProblemSet(problemSet, user, problemEntity, findFirstPageable);
            boolean isAc = firstAcSubmission.size() == 1;
            // 如果没有ac，则我们要统计所有的提交。此时我们把时间限制设置为当前时间，可以达到我们的需求
            Date firstAcTime = isAc ? firstAcSubmission.get(0).getCreateTime() : new Date();
            long wrongAnswerAmount = submissionRepository.getUserProblemWaAmountByInProblemSet(problemSet.getId(), user.getId(), problemEntity, firstAcTime);
            if (isAc) {
                // 此题目拿到了AC， 通过总数加一
                totalAcAmount += 1;
                // 计算总罚时
                totalTimePenalty += countProblemPenalty(
                        problemSet.getStartTime(),
                        firstAcSubmission.get(0).getCreateTime(),
                        problemSet.getTimePenalty(),
                        wrongAnswerAmount
                );
            }
            problemList.add(generateProblemCondition(wrongAnswerAmount, problemSet, isAc ? firstAcSubmission.get(0) : null, problemEntity));
        }
        return new TeamProblemsSolutionBO(problemList, totalAcAmount, totalTimePenalty);
    }

    /**
     * 获取问题的解决状态，我们最终返回一个map，样例如下:
     * {
     * "tryAmount": 尝试的次数,
     * "timeCost": 首次ac的时间，从题目集的开始时间算起,
     * "isFirstAc": 是否是第一个ac的（一血）,
     * "isAccepted": false（是否通过了这道题）
     * },
     *
     * @param wrongAnswerAmount 错误答案的数量
     * @param problemSetEntity  题目集实体类
     * @param firstAcSubmission 首次ac的提交
     * @param problemEntity     题目
     * @author yuzhanglong
     * @date 2020-08-13 22:42:39
     */
    private Map<String, Object> generateProblemCondition(
            Long wrongAnswerAmount,
            ProblemSetEntity problemSetEntity,
            SubmissionEntity firstAcSubmission, JudgeProblemEntity problemEntity) {
        Map<String, Object> problemCondition = new HashMap<>(3);
        boolean isAc = firstAcSubmission != null;
        // 是否 ac
        problemCondition.put("isAccepted", isAc);
        // 之前是否已经有人ac了
        boolean hasEarlyAcBefore = false;
        if (isAc) {
            hasEarlyAcBefore = submissionRepository.countAcSubmissionEarlyThanGiven(problemSetEntity.getId(), problemEntity, firstAcSubmission.getCreateTime()) == 0;
        }
        problemCondition.put("isFirstAc", hasEarlyAcBefore);
        // 尝试次数
        // 需要注意的是，ac之后如果这道题再被提交，我们不会把这些「多余」的提交计算进去
        long tryAmount = isAc ? (wrongAnswerAmount + 1) : wrongAnswerAmount;
        problemCondition.put("tryAmount", tryAmount);
        problemCondition.put("timeCost", isAc ? DateTimeUtil.countTimeCostInMinute(problemSetEntity.getStartTime(), firstAcSubmission.getCreateTime()) : 0);
        return problemCondition;
    }

    /**
     * 计算某道题的罚时
     * penalty 的计算方法：
     * 每道试题用时将从竞赛开始到试题解答被判定为正确（AC）为止，
     * 其间每一次提交运行结果被判错误的话将被加罚20分钟时间，
     * 未正确解答的试题不记时
     *
     * @param timePenalty       每一次错误提交罚时
     * @param wrongAnswerAmount 错误答案的数量
     * @param acTime            首次ac的时间
     * @param startTime         题目集开始时间
     * @author yuzhanglong
     * @date 2020-08-13 18:10:19
     */
    private Long countProblemPenalty(Date startTime, Date acTime, Long timePenalty, Long wrongAnswerAmount) {
        long timeBetweenAcTimeAndStartTime = DateTimeUtil.countTimeCostInMinute(startTime, acTime);
        long res = timePenalty * wrongAnswerAmount + timeBetweenAcTimeAndStartTime;
        return res >= 0 ? res : 0;
    }


    /**
     * 通过题目集id 我们从缓存中获取题目集的记分板信息
     * 记分板的大数据量、大计算量决定了我们必须使用缓存
     *
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-08-15 00:08:49
     */
    public Object getProblemSetScoreBoardCache(Long problemSetId) {
        // 校验
        ProblemSetEntity problemSetEntity = validateAndGetProblemSetById(problemSetId);
        return problemSetCache.getProblemSetScoreBoardCache(problemSetId);
    }

    /**
     * 更新题目集的基本信息
     *
     * @param problemSetId  题目集id
     * @param problemSetDTO 题目集相关数据传输对象
     * @author yuzhanglong
     * @date 2020-08-15 22:58:26
     */
    public void updateProblemSetBasicInfo(Long problemSetId, ProblemSetDTO problemSetDTO) {
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        problemSetEntity.setName(problemSetDTO.getName());
        problemSetEntity.setDescription(problemSetDTO.getDescription());
        problemSetEntity.setStartTime(problemSetDTO.getStartTime());
        problemSetEntity.setDeadline(problemSetDTO.getDeadline());
        problemSetEntity.setAllowedLanguage(problemSetDTO.getAllowedLanguage());
        problemSetEntity.setOpen(problemSetDTO.getOpen());
        problemSetRepository.save(problemSetEntity);
    }

    /**
     * 查询、处理某个题目集活跃区间内的统计数据
     *
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-8-20 10:54:52
     */
    public CountSubmissionByTimeVO countProblemSetSubmission(Long problemSetId) {
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        Date start = problemSetEntity.getStartTime();
        Date end = problemSetEntity.getDeadline();
        Set<List<Object>> results = submissionRepository.countSubmissionGroupByHoursByProblemSetId(start, end, problemSetId);
        List<Map<String, Object>> items = SubmissionService.publishSubmissionHourlyCount(results);
        return new CountSubmissionByTimeVO((long) results.size(), items);
    }

    /**
     * 查询题目集散点结果列表
     * 单个元素示例:
     * {
     * "judge_result": "ACCEPT",
     * "userNickName": "yzl",
     * "time": 1597681469000
     * },
     *
     * @param problemSetId 题目集id
     * @return 题目集散点结果列表
     * @author yuzhanglong
     * @date 2020-8-20 14:36:12
     */
    public List<Map<String, Object>> countSubmissionConditionScatter(Long problemSetId) {
        List<SubmissionEntity> res = submissionRepository.countSubmissionConditionScatterByProblemSetId(problemSetId);
        List<Map<String, Object>> result = new ArrayList<>();
        for (SubmissionEntity re : res) {
            Map<String, Object> tmp = new HashMap<>();
            tmp.put("judgeResult", re.getJudgeCondition());
            tmp.put("userNickName", re.getCreator().getNickname());
            tmp.put("time", re.getCreateTime());
            result.add(tmp);
        }
        return result;
    }

    /**
     * 获取题目集时间线
     *
     * @param problemSetId 题目集id
     * @author yuzhanglong
     * @date 2020-8-20 14:36:12
     */
    public List<Map<String, Object>> getProblemSetTimeLine(Long problemSetId) {
        ProblemSetEntity problemSetEntity = validateAndGetProblemSetById(problemSetId);
        // 获取题目集下的所有问题
        List<JudgeProblemEntity> problems = problemSetEntity.getProblems();
        List<Map<String, Object>> result = new ArrayList<>();
        Map<Long, Boolean> acAppearCondition = new HashMap<>(10);
        Set<List<Object>> res = submissionRepository.getAcSubmissionByProblemSet(problemSetEntity);
        for (List<Object> re : res) {
            Map<String, Object> tmp = new HashMap<>(10);
            // 创建者
            UserEntity user = (UserEntity) re.get(0);
            tmp.put("creator", mapper.map(user, UserInfoBasicVO.class));
            // ac时间
            tmp.put("acTime", re.get(1));
            // 本次提交对应的题目
            JudgeProblemEntity targetProblem = (JudgeProblemEntity) re.get(2);
            tmp.put("problemIndex", getProblemIndexInProblemSet(problems, targetProblem));
            // 如果没有出现过AC，即一血，我们将isFirstAc键置为true
            boolean isAcBefore = acAppearCondition.get(targetProblem.getId()) != null;
            if (isAcBefore) {
                tmp.put("isFirstAc", false);
            } else {
                tmp.put("isFirstAc", true);
                acAppearCondition.put(targetProblem.getId(), true);
            }
            result.add(tmp);
        }
        return result;
    }

    /**
     * 获取题目集问题索引
     *
     * @param judgeProblemEntityList 问题实体类
     * @param problemEntity          问题实体类
     * @author yuzhanglong
     * @date 2020-8-20 14:36:12
     */
    private Integer getProblemIndexInProblemSet(List<JudgeProblemEntity> judgeProblemEntityList, JudgeProblemEntity problemEntity) {
        for (int i = 0; i < judgeProblemEntityList.size(); i++) {
            if (judgeProblemEntityList.get(i).getId().equals(problemEntity.getId())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除题目集
     *
     * @param problemSetId id
     * @author yuzhanglong
     * @date 2020-9-6 14:38:33
     */
    public void deleteProblemSet(Long problemSetId) {
        ProblemSetEntity problemSetEntity = problemSetRepository.findOneById(problemSetId);
        if (problemSetEntity == null) {
            throw new NotFoundException("B0011");
        }
        problemSetRepository.delete(problemSetEntity);
    }
}