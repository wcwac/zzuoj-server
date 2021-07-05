package cn.edu.zzu.oj.controller;


import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.anotation.UserSession;
import cn.edu.zzu.oj.client.SubmissionClient;
import cn.edu.zzu.oj.converter.EntityToFront;
import cn.edu.zzu.oj.entity.Contest;
import cn.edu.zzu.oj.entity.frontToWeb.ContestFront;
import cn.edu.zzu.oj.entity.frontToWeb.RankingFront;
import cn.edu.zzu.oj.entity.frontToWeb.SolutionFront;
import cn.edu.zzu.oj.entity.jwt.UserSessionDTO;
import cn.edu.zzu.oj.service.impl.ContestServiceImpl;
import cn.edu.zzu.oj.util.DateUtilY;
import cn.edu.zzu.oj.util.UserSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-12
 */
@BaseResponse
@RestController
@RequestMapping("/contest")
public class ContestController {
    private static Logger log = LoggerFactory.getLogger(ContestController.class);

    @Autowired
    ContestServiceImpl contestService;

    @Autowired
    SubmissionClient submissionClient;

    //pos表示的是相对于第一条记录的偏移，数据库中第一条记录pos为0，
    @GetMapping("/show")
    public List<Contest> show(@RequestParam("pos") Integer pos, @RequestParam("limit") Integer limit, @UserSession UserSessionDTO userSessionDTO) {
        List<Contest> list = null;
        try {
            list = contestService.getContestsByPage(pos, limit);

            System.out.println(userSessionDTO);
            if (userSessionDTO == null || UserSessionUtil.isAdmin(userSessionDTO)) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setGroupId(null).setPassword(null).setProblems(null);
                }
            }
        } catch (Exception e) {
            log.error("show contests list error: " + e.getMessage());
            return null;
        }
        return list;
    }

    @GetMapping("/cnt")
    public Integer getContests(@UserSession UserSessionDTO userMeta) {
        System.out.println(userMeta);
        return contestService.getContestCnt();
    }


    @GetMapping("/get")
    public ContestFront getContestById(@RequestParam("contestId") Integer contestId, @UserSession UserSessionDTO userSessionDTO) {
        ContestFront contestFront = null;
        try {
            Contest contest = contestService.getContestByContestId(contestId);
            contestFront = EntityToFront.ContestToContestFront(contest);

            if (userSessionDTO == null || !UserSessionUtil.isAdmin(userSessionDTO)) {
                contestFront.setGroupId(null).setPassword(null);
            }
        } catch (Exception e) {
            return null;
        }
        System.out.println("+++++");
        System.out.println(contestFront);
        return contestFront;
    }

    @GetMapping("/ranking")
    public List<RankingFront> getContestRanking(@RequestParam("contestId") Integer contestId, @UserSession UserSessionDTO userSessionDTO) {
        List<RankingFront> rankingFronts =  new ArrayList<>();
        try {
            //先查出所有submission
            List<SolutionFront> solutionFronts = submissionClient.getSubmission(contestId);
            //获取题目信息
            Contest contest = contestService.getContestByContestId(contestId);
            String[] problems = contest.getProblems().split("\\^");
            Map<String, Integer> problemIndex = new HashMap<>();
            for(int i=0; i<problems.length; i++){
                problemIndex.put(problems[i], i);
            }
            int problemCnt = problems.length;

            //获取所有用户
            int p = 0;
            Map<String,Integer> uids = new HashMap<>();
            for(int i=0; i<solutionFronts.size(); i++){
                SolutionFront solution = solutionFronts.get(i);
                int pos = -1;
                if( uids.containsKey(solution.getUserId()) ){
                    //包含
                    pos = uids.get(solution.getUserId());
                } else {
                    //没有包含
                    uids.put(solution.getUserId(), p);
                    pos = p;
                    p++;
                    RankingFront tempRankingFront = new RankingFront().setAc(0)
                            .setUserId(solution.getUserId())
                            .setSecond(0)
                            .setProblems(problems)
                            .setAcDate(new Date[problemCnt])
                            .setErrorCnt(new Integer[problemCnt]);
                    for(int j=0; j<problemCnt; j++){
                        tempRankingFront.getAcDate()[j] = null;
                        tempRankingFront.getErrorCnt()[j] = 0;
                    }
                    rankingFronts.add(tempRankingFront);
                }
                //开始修改本用户本题目的信息

                //取出当前用户的排名
                RankingFront rankingFront = rankingFronts.get(pos);
                //todo:修改 逻辑有问题

                int pIdx = problemIndex.get(String.valueOf(solution.getProblemId()));
                if(solution.getResult().equals("Accepted")){
                    //当前次ac
                    rankingFront.getAcDate()[pIdx] = solution.getInDate();
                    Integer diff = DateUtilY.calLastedTime(contest.getStartTime(), solution.getInDate()); //ac距离比赛开始的时间
                    rankingFront.setSecond( rankingFront.getSecond() + diff);
                }else{
                    //当前次error
                    rankingFront.getErrorCnt()[pIdx] = rankingFront.getErrorCnt()[pIdx] - 1  ;
                    rankingFront.setSecond( rankingFront.getSecond() + 20 * 60 );//加上20分钟
                }
            }
            //计算ac的次数
            for(int i=0; i<rankingFronts.size(); i++){
                int acCnt = 0;
                for(int j=0; j < rankingFronts.get(i).getProblems().length; j++){
                    if( rankingFronts.get(i).getAcDate()[j] != null ){
                        acCnt++;
                    }
                }
                rankingFronts.get(i).setAc(acCnt);
            }
            //排序
            Collections.sort(rankingFronts, new Comparator<RankingFront>() {
                @Override
                public int compare(RankingFront o1, RankingFront o2) {
                    if(o1.getAc() != o2.getAc()){
                        return o2.getAc() - o1.getAc();
                    }
                    return o1.getSecond() - o2.getAc();
                }
            });

        } catch (Exception e) {
            return null;
        }
        return rankingFronts;
    }
}