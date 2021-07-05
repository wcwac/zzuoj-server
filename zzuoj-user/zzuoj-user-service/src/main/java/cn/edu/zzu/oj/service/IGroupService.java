package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    public List<Group> getGroupPage(Integer pos, Integer limit) throws Exception;

    public Integer getGroupCnt() throws Exception;

    public Group getGroupById(String group_id) throws Exception;

    public Integer updateGroupById(Group group) throws Exception;

}
