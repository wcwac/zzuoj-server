package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.entity.Checkpoint;
import cn.edu.zzu.oj.mapper.CheckpointMapper;
import cn.edu.zzu.oj.service.ICheckpointService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
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
 * @since 2021-05-18
 */
@Service
public class CheckpointServiceImpl extends ServiceImpl<CheckpointMapper, Checkpoint> implements ICheckpointService, IService<Checkpoint> {
    @Autowired
    CheckpointMapper checkpointMapper;

    @Override
    public Integer deleteCheckPointById(Long cId) {
        return checkpointMapper.deleteById(cId);
    }

    @Override
    public List<Checkpoint> queryByPid(Integer pId) {
        QueryWrapper<Checkpoint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("problem_id", pId);
        return checkpointMapper.selectList(queryWrapper);
    }

    @Override
    public Integer deleteAllPointsByPid(Integer pId) {
        QueryWrapper<Checkpoint> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("problem_id", pId);
        return checkpointMapper.delete(queryWrapper);
    }
}
