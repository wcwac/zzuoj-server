package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.entity.Solution;
import cn.edu.zzu.oj.mapper.SolutionMapper;
import cn.edu.zzu.oj.service.ISolutionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
