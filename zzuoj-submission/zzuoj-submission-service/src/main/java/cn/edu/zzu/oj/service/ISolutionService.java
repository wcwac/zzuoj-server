package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.Solution;
import com.baomidou.mybatisplus.extension.service.IService;
import org.omg.CORBA.INTERNAL;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.parsing.Problem;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-06-02
 */
public interface ISolutionService extends IService<Solution> {

    //获取所有的
    public List<Solution> getSolutionsPage(Integer pos, Integer limit) throws Exception;

    //通过userId分页查询
    public List<Solution> getSolutionsPageByUid(Integer pos, Integer limit, String userId) throws Exception;

    //通过problemId查询
    public List<Solution> getSolutionsPageByPid(Integer pos, Integer limit, Integer pId) throws Exception;

    //通过userId、problemId查询
    public List<Solution> getSolutionsPageByUidPid(Integer pos, Integer limit, String userId, Integer pId) throws Exception;

    //通过contestId查询
    public List<Solution> getSolutionsPageByContestId(Integer pos, Integer limit, Integer contestId) throws Exception;

    public List<Solution> getSolutionRanking(Integer contestId) throws Exception;


    public Integer getSolutionCnt();
    public Integer getSolutionCntByUid(String uid);
    public Integer getSolutionCntByPid(Integer pid);
    public Integer getSolutionCntByUidPid(String uid, Integer pid);
    public Integer getSolutionCntByContestId(Integer contestId);

}
