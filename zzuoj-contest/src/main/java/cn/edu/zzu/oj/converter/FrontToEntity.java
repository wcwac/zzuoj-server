package cn.edu.zzu.oj.converter;

import cn.edu.zzu.oj.entity.Contest;
import cn.edu.zzu.oj.entity.frontToWeb.ContestFront;
import cn.edu.zzu.oj.util.ContestUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrontToEntity {
    public static Contest ContestFrontToContest(ContestFront contestFront){
        Contest res = new Contest().setContestId(contestFront.getContestId())
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
