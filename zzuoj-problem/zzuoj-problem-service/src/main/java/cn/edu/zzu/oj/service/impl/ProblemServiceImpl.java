package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.entity.Problem;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.mapper.ProblemMapper;
import cn.edu.zzu.oj.service.IProblemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-04-11
 */
@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements IProblemService {

    private static Logger log = LoggerFactory.getLogger(ProblemServiceImpl.class);

    @Autowired
    ProblemMapper problemMapper;

    @Override
    public List<Problem> getProblemsPage(Integer pos, Integer limit) throws Exception {
        List<Problem> records = null;
        try {
            Page<Problem> page = new Page<>(pos,limit);
            IPage<Problem> problemIPage = problemMapper.selectPage(page, new QueryWrapper<Problem>());
            records = problemIPage.getRecords();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return records;
    }

    @Override
    public List<Problem> getProblemByIds(List<Integer> ids) {
        List<Problem> problems = null;
        try {
            problems = problemMapper.selectBatchIds(ids);
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return problems;
    }

    @Override
    public Integer deleteProblemById(Integer id) {
        Integer cnt = 0;
        try {
            cnt =  problemMapper.deleteById(id);
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }

    @Override
    public Integer getProblemCnt() {
        QueryWrapper<Problem> queryWrapper = new QueryWrapper<Problem>();
        return problemMapper.selectCount(queryWrapper);
    }

    @Override
    public Integer addProblem(Problem problem) {
        Integer cnt = 0;
        try {
            cnt = problemMapper.insert(problem);
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }

    @Override
    public Integer switchDefunctStatusByPid(Integer pid, String status) {
        Integer cnt = 0;
        try {
            cnt = problemMapper.updateProblemDefunctStatus(new Problem().setProblemId(pid).setDefunct(status));
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }

    @Override
    public Integer incre(Integer pid, boolean ac) {
        Integer cnt = 0;
        if(ac){
            cnt = problemMapper.increSubmitAC(pid);
        }else{
            cnt = problemMapper.increSubmit(pid);
        }
        return cnt;
    }


}
