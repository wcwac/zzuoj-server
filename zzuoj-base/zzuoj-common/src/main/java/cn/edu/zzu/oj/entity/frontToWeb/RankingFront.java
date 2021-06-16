package cn.edu.zzu.oj.entity.frontToWeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingFront {

    String userId;

    Integer ac;

    //总花费时间+罚时
    Integer second;

    String[] problems;
    Date[] acDate;
    Integer[] errorCnt;
}
