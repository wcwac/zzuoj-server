package cn.edu.zzu.oj.converter;

import cn.edu.zzu.oj.entity.SourceCode;
import cn.edu.zzu.oj.entity.SourceT;

public class Converter {
    public static SourceT SourceCodeToSourceT(SourceCode sourceCode, String path){
        SourceT sourceT = new SourceT().setFilePath(path)
                .setCodeLength(sourceCode.getCodeLength())
                .setContestId(sourceCode.getContestId())
                .setInDate(sourceCode.getInDate())
                .setLanguage(sourceCode.getLanguage())
                .setNum(sourceCode.getNum())
                .setProblemId(sourceCode.getProblemId())
                .setSolutionId(sourceCode.getSolutionId())
                .setUserId(sourceCode.getUserId())
                .setValid(sourceCode.getValid());
        return sourceT;
    }
}
