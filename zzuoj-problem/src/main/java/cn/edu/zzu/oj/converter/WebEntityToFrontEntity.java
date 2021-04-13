package cn.edu.zzu.oj.converter;

import cn.edu.zzu.oj.entity.Problem;
import cn.edu.zzu.oj.entity.front.ProblemFront;

import java.util.ArrayList;
import java.util.List;

public class WebEntityToFrontEntity {

    public static List<ProblemFront> ProblemToProblemFront(List<Problem> problems){
        List<ProblemFront> res = new ArrayList<>();
        for(Problem v : problems){
            ProblemFront temp = new ProblemFront().setProblemId(v.getProblemId())
                    .setAccepted(v.getAccepted())
                    .setDefunct(v.getDefunct())
                    .setSubmit(v.getSubmit())
                    .setTitle(v.getTitle());
            res.add(temp);
        }
        return res;
    }
}
