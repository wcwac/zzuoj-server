package cn.edu.zzu.oj.controller;

import cn.edu.zzu.oj.api.JudgeInternalApi;
import cn.edu.zzu.oj.client.ProblemClient;
import cn.edu.zzu.oj.client.UserClient;
import cn.edu.zzu.oj.entity.Solution;
import cn.edu.zzu.oj.entity.SourceT;
import cn.edu.zzu.oj.enums.CodeStatus;
import cn.edu.zzu.oj.service.impl.CompileinfoServiceImpl;
import cn.edu.zzu.oj.service.impl.RuntimeinfoServiceImpl;
import cn.edu.zzu.oj.service.impl.SolutionServiceImpl;
import cn.edu.zzu.oj.util.JudgeResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;

import static cn.edu.zzu.oj.util.cmd.callExec;

@RestController
public class JudgeInternalController implements JudgeInternalApi {

    @Autowired
    SolutionServiceImpl solutionService;

    @Autowired
    RuntimeinfoServiceImpl runtimeinfoService;

    @Autowired
    CompileinfoServiceImpl compileinfoService;

//    @Autowired
//    ProblemClient problemClient;
//
//    @Autowired
//    UserClient userClient;



    @Override
    public Integer judge(SourceT source) {
        File file = new File("/Users/bytedance/yly/zzuoj/deer-executor");
        String main  = "./main.go";

        String checkPoints = "./data/problems/APlusB/problem.json";
        String codePath = source.getFilePath();

        System.out.println(source);
        String[] cmds3 = {"go","run",main, "run",checkPoints,codePath};

        StringBuilder res = null;
        Solution solution = null;
        try {
            res = callExec(cmds3, file);
            System.out.println(res);
            CodeStatus status = JudgeResultUtil.parseResult(new String(res));

            solution = new Solution().setInDate(source.getInDate())
                    .setContestId(source.getContestId())
                    .setCodeLength(source.getCodeLength())
                    .setSolutionId(source.getSolutionId())
                    .setLanguage(source.getLanguage())
                    .setNum(source.getNum())
                    .setPassRate(null)
                    .setProblemId(source.getProblemId())
                    .setResult(status.value())
                    .setUserId(source.getUserId())
                    .setValid(source.getValid());

            if(status.value() == 0){
                //ac
                Integer[] timeAMemory = JudgeResultUtil.parseAc(new String(res));
                solution.setMemory(timeAMemory[1]) //使用内存
                        .setTime(timeAMemory[0]); // 耗费时间，
            }
            solutionService.save(solution);

        } catch (Exception e) {
            return 0;
        }


        //更新题目信息，更新用户信息。
//        problemClient.increSubmitAc(solution.getProblemId(), solution.getResult() == 0? true : false);
//        userClient.increSubmitAc(solution.getUserId(), solution.getResult() == 0? true : false);

        return 1;
    }
}
