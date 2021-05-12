package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.entity.Group;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.mapper.GroupMapper;
import cn.edu.zzu.oj.service.IGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
            cnt = groupMapper.deleteById(group_id);
        } catch (BaseException e){
            throw new BaseException(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }
        return cnt;
    }
}
