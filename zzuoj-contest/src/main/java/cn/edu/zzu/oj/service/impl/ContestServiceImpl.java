package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.entity.Contest;
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
public class ContestServiceImpl extends ServiceImpl<ContestMapper, Contest> implements IContestService {
    @Autowired
    ContestMapper contestMapper;

    @Override
    public Integer addContest(Contest contest) throws Exception {
        Integer cnt = 0;
        try{
            cnt = contestMapper.insert(contest);
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
    public Integer updateContestByContestId(Contest contest) {
        Integer cnt = 0;
        try {
            cnt = contestMapper.updateById(contest);
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }

    @Override
    public Contest getContestByContestId(Integer contestId) {
        Contest res = null;
        try {
            res = contestMapper.selectById(contestId);
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return res;
    }

    @Override
    public List<Contest> getContestsByPage(Integer pos, Integer limit) throws Exception {
        List<Contest> records = null;
        try {
            Page<Contest> page = new Page<>(pos,limit);
            IPage<Contest> contestIPage = contestMapper.selectPage(page, new QueryWrapper<Contest>());
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
            cnt = contestMapper.selectCount(new QueryWrapper<Contest>());
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }

    @Override
    public Integer switchDefunct(Contest contest) {
        Integer cnt = 0;
        try {
            cnt = contestMapper.updateContestDefunctStatus(contest);
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }
}
