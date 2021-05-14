package cn.edu.zzu.oj.converter;

import cn.edu.zzu.oj.entity.ContestT;
import cn.edu.zzu.oj.entity.frontToWeb.ContestFront;
import cn.edu.zzu.oj.util.ContestUtil;

public class FrontToEntity {
    public static ContestT ContestFrontToContest(ContestFront contestFront){
        ContestT res = new ContestT().setContestId(contestFront.getContestId())
                .setTitle( contestFront.getTitle() )
                .setDefunct( contestFront.getDefunct() )
                .setDescription( contestFront.getDescription() )
                .setIsPrivate( contestFront.getIsPrivate() )
                .setPassword( contestFront.getPassword() )
                .setGroupId( contestFront.getGroupId() )
                .setProblems( contestFront.getProblems() );

        res.setStartTime( contestFront.getTime().get(0) )
                .setEndTime( contestFront.getTime().get(1) );

        res.setLangmask( ContestUtil.getLangMask(contestFront.getLangmask()) );

        return res;
    }
}
