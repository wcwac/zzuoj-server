package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.entity.Group;
import cn.edu.zzu.oj.entity.Group;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.mapper.GroupMapper;
import cn.edu.zzu.oj.service.IGroupService;
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
 * @since 2021-05-12
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    @Autowired
    GroupMapper groupMapper;

    @Override
    public Integer addGroup(Group group) {
        Integer cnt = 0;
        try {
            cnt = groupMapper.insert(group);
        } catch (BaseException e){
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return cnt;
    }

    @Override
    public Integer deleteGroup(String group_id) {
        Integer cnt = 0;
        try {
            QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("group_id", group_id);
            cnt = groupMapper.delete(queryWrapper);
        } catch (BaseException e){
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return cnt;
    }

    public List<Group> getGroupPage(Integer pos, Integer limit) throws Exception {
        List<Group> records = null;
        try {
            System.out.println("pos:"+pos+ "  limit:"+limit);
            Page<Group> page = new Page<>(pos,limit);
            IPage<Group> groupIPage = groupMapper.selectPage(page, new QueryWrapper<Group>());
            records = groupIPage.getRecords();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return records;
    }

    @Override
    public Integer getGroupCnt() throws Exception {
        Integer cnt = null;
        try {
            QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>();
            cnt = groupMapper.selectCount(queryWrapper);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return cnt;
    }

    @Override
    public Group getGroupById(String group_id) throws Exception {
        Group res = null;
        try {
            QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>();
            queryWrapper.eq("group_id", group_id);
            res = groupMapper.selectOne(queryWrapper);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return res;
    }

    @Override
    public Integer updateGroupById(Group group) throws Exception {
        Integer cnt = null;
        try {
            QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("group_id", group.getGroupId());
            cnt = groupMapper.update(group, queryWrapper);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return cnt;
    }

}
