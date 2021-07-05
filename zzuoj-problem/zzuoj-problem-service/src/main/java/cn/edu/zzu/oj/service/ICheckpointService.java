package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.Checkpoint;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-18
 */
public interface ICheckpointService extends IService<Checkpoint> {

    //通过checkPoint_id删除测试点
    public Integer deleteCheckPointById(Long cId);

    //通过问题id获取所有测试点
    public List<Checkpoint> queryByPid(Integer pId);

    //通过问题id删除所有测试点
    public Integer deleteAllPointsByPid(Integer pId);
}
