package cn.edu.zzu.oj.converter;

import cn.edu.zzu.oj.entity.Solution;
import cn.edu.zzu.oj.entity.frontToWeb.SolutionFront;
import cn.edu.zzu.oj.enums.CodeStatus;

import java.util.ArrayList;
import java.util.List;

public class EntityToFrontEntity {
    public static List<SolutionFront> SolutionToSolutionFront(List<Solution> solutionList){
        List<SolutionFront> solutionFronts = new ArrayList<>();
        for(int i=0; i<solutionList.size(); i++){
            Solution temp = solutionList.get(i);
            SolutionFront sf = new SolutionFront();
            sf.setCodeLength(temp.getCodeLength())
                    .setContestId(temp.getContestId())
                    .setInDate(temp.getInDate())
                    .setLanguage(temp.getLanguage())
                    .setMemory(temp.getMemory())
                    .setNum(temp.getNum())
                    .setPassRate(temp.getPassRate())
                    .setProblemId(temp.getProblemId())
                    .setResult(CodeStatus.getCodeStatusById(temp.getResult()).getMsg())
                    .setSolutionId(temp.getSolutionId())
                    .setTime(temp.getTime())
                    .setUserId(temp.getUserId())
                    .setValid(temp.getValid());

            solutionFronts.add(sf);
        }
        return solutionFronts;
    }
}
