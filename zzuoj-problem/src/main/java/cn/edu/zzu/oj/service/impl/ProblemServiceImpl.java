package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.entity.Problem;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.mapper.ProblemMapper;
import cn.edu.zzu.oj.service.IProblemService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements IProblemService {

    @Autowired
    ProblemMapper problemMapper;

    @Override
    public IPage<Problem> getProblemsPage(Page<Problem> page) {
        return problemMapper.selectPageVo(page);
    }

    //todo: 网络、db出现问题,是否在后端开启事务
    @Override
    public Integer insertProblems(List<Problem> problemList) {
        Integer cnt = 0;
        for(Problem v : problemList){
            try{
                cnt += problemMapper.insert(v);
            } catch (Exception e){
                log.error("insert problems error: %s",e.getMessage());
                throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return cnt;
    }

}
