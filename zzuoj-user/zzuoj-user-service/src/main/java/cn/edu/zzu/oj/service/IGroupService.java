package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-12
 */
public interface IGroupService extends IService<Group> {


    public Integer addGroup(Group group);

    public Integer deleteGroup(String group_id);

}
