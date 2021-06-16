package cn.edu.zzu.oj;

import cn.edu.zzu.oj.entity.frontToWeb.SolutionFront;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/internal/submission")
public interface SubmissionApi {
    String SERVICE_NAME = "zzuoj-submission";

    @GetMapping("/getSubmission")
    List<SolutionFront> getSubmission(@RequestParam("contestId") Integer contestId);
}