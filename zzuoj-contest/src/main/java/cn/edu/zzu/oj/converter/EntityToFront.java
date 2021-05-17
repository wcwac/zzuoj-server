package cn.edu.zzu.oj.converter;

import cn.edu.zzu.oj.entity.Contest;
import cn.edu.zzu.oj.entity.frontToWeb.ContestFront;
import cn.edu.zzu.oj.util.ContestUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntityToFront {
    public static ContestFront ContestToContestFront(Contest contest){
        ContestFront res = new ContestFront().setTitle( contest.getTitle() )
                .setDefunct( contest.getDefunct() )
                .setDescription( contest.getDescription() )
                .setIsPrivate( contest.getIsPrivate() )
                .setPassword( contest.getPassword() )
                .setGroupId( contest.getGroupId() )
                .setProblems( contest.getProblems() )
                .setUserId( contest.getUserId() );

        List<Date> dateTemp = new ArrayList<>();
        dateTemp.add( contest.getStartTime() );
        dateTemp.add( contest.getEndTime() );
        res.setTime( dateTemp );

        res.setLangmask(ContestUtil.getLangString( contest.getLangmask()) );

        return res;
    }
}
