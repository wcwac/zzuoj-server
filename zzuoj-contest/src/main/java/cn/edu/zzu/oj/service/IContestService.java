package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.Contest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-13
 */
public interface IContestService extends IService<Contest> {
    //add
    public Integer addContest(Contest contest) throws Exception;

    //delete
    public Integer deleteContestByContestId(Integer contestId);

    //update
    public Integer updateContestByContestId(Contest contest);

    //get
    public Contest getContestByContestId(Integer contestId);

    //get contests
    public List<Contest> getContestsByPage(Integer pos, Integer limit) throws Exception;

    //get cnt
    public Integer getContestCnt();

    //switchDefunct
    public Integer switchDefunct(Contest contest);
}
