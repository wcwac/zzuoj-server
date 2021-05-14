package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.entity.ContestT;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.mapper.ContestMapper;
import cn.edu.zzu.oj.service.IContestService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-13
 */
@Service
public class ContestServiceImpl extends ServiceImpl<ContestMapper, ContestT> implements IContestService {
    @Autowired
    ContestMapper contestMapper;

    @Override
    public Integer addContest(ContestT contestT) throws Exception {
        Integer cnt = 0;
        try{
            cnt = contestMapper.insert(contestT);
        } catch (Exception e){
            throw new Exception();
        }
        return cnt;
    }

    @Override
    public Integer deleteContestByContestId(Integer contestId) {
        Integer cnt = 0;
        try {
            cnt = contestMapper.deleteById(contestId);
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }

    @Override
    public Integer updateContestByContestId(ContestT contestT) {
        Integer cnt = 0;
        try {
            cnt = contestMapper.updateById(contestT);
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }

    @Override
    public ContestT getContestByContestId(Integer contestId) {
        ContestT res = null;
        try {
            res = contestMapper.selectById(contestId);
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

    @Override
    public List<ContestT> getContestsByPage(Integer pos, Integer limit) throws Exception {
        List<ContestT> records = null;
        try {
            Page<ContestT> page = new Page<>(pos,limit);
            IPage<ContestT> contestIPage = contestMapper.selectPage(page, new QueryWrapper<ContestT>());
            records = contestIPage.getRecords();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return records;
    }

    @Override
    public Integer getContestCnt() {
        Integer cnt = 0;
        try {
            cnt = contestMapper.selectCount(new QueryWrapper<ContestT>());
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }

    @Override
    public Integer switchDefunct(ContestT contestT) {
        Integer cnt = 0;
        try {
            cnt = contestMapper.updateContestDefunctStatus(contestT);
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }
}
