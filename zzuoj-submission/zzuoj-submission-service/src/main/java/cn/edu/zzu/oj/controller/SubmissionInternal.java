package cn.edu.zzu.oj.controller;


import cn.edu.zzu.oj.SubmissionApi;
import cn.edu.zzu.oj.converter.EntityToFrontEntity;
import cn.edu.zzu.oj.entity.Solution;
import cn.edu.zzu.oj.entity.frontToWeb.SolutionFront;
import cn.edu.zzu.oj.service.impl.SolutionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class SubmissionInternal implements SubmissionApi {

    @Autowired
    SolutionServiceImpl solutionService;

    @Override
    public List<SolutionFront> getSubmission(Integer contestId) {
        List<Solution> list = null;
        try {
            list = solutionService.getSolutionRanking(contestId);
        } catch (Exception e){
            log.error("show solutions list error: " + e.toString());
            return null;
        }
        return EntityToFrontEntity.SolutionToSolutionFront(list);
    }
}
