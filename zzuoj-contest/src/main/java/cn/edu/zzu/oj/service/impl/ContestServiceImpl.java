package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.entity.Contest;
import cn.edu.zzu.oj.mapper.ContestMapper;
import cn.edu.zzu.oj.service.IContestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-12
 */
@Service
public class ContestServiceImpl extends ServiceImpl<ContestMapper, Contest> implements IContestService {

    @Override
    public Integer addContest(Contest conest) {
        return null;
    }
}
