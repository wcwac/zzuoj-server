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
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

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
@Log4j2
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements IProblemService {

    @Autowired
    ProblemMapper problemMapper;

    @Override
    public List<Problem> getProblemsPage(Integer pos, Integer limit) {
        Page<Problem> page = new Page<>(pos,limit);

        IPage<Problem> problemIPage = problemMapper.selectPage(page, new QueryWrapper<Problem>());
        List<Problem> records = problemIPage.getRecords();

        return records;
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


}
