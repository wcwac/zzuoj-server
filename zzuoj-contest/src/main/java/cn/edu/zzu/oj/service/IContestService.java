package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.Contest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-12
 */
public interface IContestService extends IService<Contest> {

    public Integer addContest(Contest conest);

}
