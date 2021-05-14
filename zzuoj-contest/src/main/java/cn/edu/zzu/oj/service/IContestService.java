package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.ContestT;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-13
 */
public interface IContestService extends IService<ContestT> {
    //add
    public Integer addContest(ContestT contestT) throws Exception;

    //delete
    public Integer deleteContestByContestId(Integer contestId);

    //update
    public Integer updateContestByContestId(ContestT contestT);

    //get
    public ContestT getContestByContestId(Integer contestId);

    //get contests
    public List<ContestT> getContestsByPage(Integer pos, Integer limit) throws Exception;

    //get cnt
    public Integer getContestCnt();

    //switchDefunct
    public Integer switchDefunct(ContestT contestT);
}
