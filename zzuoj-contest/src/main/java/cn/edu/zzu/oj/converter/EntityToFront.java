package cn.edu.zzu.oj.converter;

import cn.edu.zzu.oj.entity.ContestT;
import cn.edu.zzu.oj.entity.frontToWeb.ContestFront;
import cn.edu.zzu.oj.util.ContestUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntityToFront {
    public static ContestFront ContestToContestFront(ContestT contestT){
        ContestFront res = new ContestFront().setTitle( contestT.getTitle() )
                .setDefunct( contestT.getDefunct() )
                .setDescription( contestT.getDescription() )
                .setIsPrivate( contestT.getIsPrivate() )
                .setPassword( contestT.getPassword() )
                .setGroupId( contestT.getGroupId() )
                .setProblems( contestT.getProblems() );

        List<Date> dateTemp = new ArrayList<>();
        dateTemp.add( contestT.getStartTime() );
        dateTemp.add( contestT.getEndTime() );
        res.setTime( dateTemp );

        res.setLangmask(ContestUtil.getLangString( contestT.getLangmask()) );

        return res;
    }
}
