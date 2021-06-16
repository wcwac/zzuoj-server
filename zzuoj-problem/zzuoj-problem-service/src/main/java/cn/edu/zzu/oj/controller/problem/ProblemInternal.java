package cn.edu.zzu.oj.controller.problem;

import cn.edu.zzu.oj.ProblemInternalApi;
import cn.edu.zzu.oj.service.impl.ProblemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemInternal implements ProblemInternalApi {

    @Autowired
    ProblemServiceImpl problemService;

    @Override
    public Integer increSubmitAc(Integer problemId, boolean ac) {
        return problemService.incre(problemId, ac);
    }
}
