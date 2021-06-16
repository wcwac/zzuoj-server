package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.entity.Solution;
import cn.edu.zzu.oj.mapper.SolutionMapper;
import cn.edu.zzu.oj.service.ISolutionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-06-02
 */
@Service
public class SolutionServiceImpl extends ServiceImpl<SolutionMapper, Solution> implements ISolutionService {

    @Autowired
    SolutionMapper solutionMapper;

    @Override
    public List<Solution> getSolutionsPage(Integer pos, Integer limit) throws Exception {
        List<Solution> records = null;
        try {
            Page<Solution> page = new Page<>(pos,limit);
            QueryWrapper<Solution> queryWrapper = new QueryWrapper<Solution>();
            queryWrapper.orderByDesc("solution_id");
            IPage<Solution> solutionIPage = solutionMapper.selectPage(page, queryWrapper);
            records = solutionIPage.getRecords();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return records;
    }

    @Override
    public List<Solution> getSolutionsPageByUid(Integer pos, Integer limit, String userId) throws Exception {
        List<Solution> records = null;
        try {
            Page<Solution> page = new Page<>(pos,limit);
            QueryWrapper<Solution> queryWrapper = new QueryWrapper<Solution>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.orderByDesc("solution_id");
            IPage<Solution> solutionIPage = solutionMapper.selectPage(page, queryWrapper);
            records = solutionIPage.getRecords();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return records;
    }

    @Override
    public List<Solution> getSolutionsPageByPid(Integer pos, Integer limit, Integer pId) throws Exception {
        List<Solution> records = null;
        try {
            Page<Solution> page = new Page<>(pos,limit);
            QueryWrapper<Solution> queryWrapper = new QueryWrapper<Solution>();
            queryWrapper.eq("problem_id", pId);
            queryWrapper.orderByDesc("solution_id");
            IPage<Solution> solutionIPage = solutionMapper.selectPage(page, queryWrapper);
            records = solutionIPage.getRecords();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return records;
    }

    @Override
    public List<Solution> getSolutionsPageByUidPid(Integer pos, Integer limit, String userId, Integer pId) throws Exception {
        List<Solution> records = null;
        try {
            Page<Solution> page = new Page<>(pos,limit);
            QueryWrapper<Solution> queryWrapper = new QueryWrapper<Solution>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("problem_id", pId);
            queryWrapper.orderByDesc("solution_id");
            IPage<Solution> solutionIPage = solutionMapper.selectPage(page, queryWrapper);
            records = solutionIPage.getRecords();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return records;
    }

    @Override
    public List<Solution> getSolutionsPageByContestId(Integer pos, Integer limit, Integer contestId) throws Exception {
        List<Solution> records = null;
        try {
            Page<Solution> page = new Page<>(pos,limit);
            QueryWrapper<Solution> queryWrapper = new QueryWrapper<Solution>();
            queryWrapper.eq("contest_id", contestId);
            queryWrapper.orderByDesc("solution_id");
            IPage<Solution> solutionIPage = solutionMapper.selectPage(page, queryWrapper);
            records = solutionIPage.getRecords();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return records;
    }

    @Override
    public List<Solution> getSolutionRanking(Integer contestId) throws Exception {
        QueryWrapper q = new QueryWrapper();
        q.eq("contest_id", contestId);
        return solutionMapper.selectList(q);
    }

    @Override
    public Integer getSolutionCnt() {
        QueryWrapper<Solution> queryWrapper = new QueryWrapper<>();
        return solutionMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer getSolutionCntByUid(String uid) {
        QueryWrapper<Solution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", uid);
        return solutionMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer getSolutionCntByPid(Integer pid) {
        QueryWrapper<Solution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("problem_id", pid);
        return solutionMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer getSolutionCntByUidPid(String uid, Integer pid) {
        QueryWrapper<Solution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",uid );
        queryWrapper.eq("problem_id", pid);
        return solutionMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer getSolutionCntByContestId(Integer contestId) {
        QueryWrapper<Solution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contest_id",contestId );
        return solutionMapper.selectCount(queryWrapper);
    }
}
