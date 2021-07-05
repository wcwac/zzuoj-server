package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.Problem;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-04-11
 */
public interface IProblemService extends IService<Problem> {

    public List<Problem> getProblemsPage(Integer pos, Integer limit) throws Exception;

    public List<Problem> getProblemsPageIncludePrivate(Integer pos, Integer limit) throws Exception;

    public List<Problem> getProblemByIds(List<Integer> ids);

    public Integer deleteProblemById(Integer id);

    public Integer getProblemCnt();

    public Integer getProblemCntIncludePrivate();

    //插入的时候返回的是自增id
    public int insert(Problem problem) ;

    public Integer addProblem(Problem problem);

    public Integer switchDefunctStatusByPid(Integer pid, String status);

    public Integer updateProblemByPid(Problem problem);

    //增加一次提交
    public Integer incre(Integer pid, boolean ac);


}
