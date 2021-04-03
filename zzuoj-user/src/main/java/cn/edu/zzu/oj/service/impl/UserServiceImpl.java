package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.entity.User;
import cn.edu.zzu.oj.mapper.UsersMapper;
import cn.edu.zzu.oj.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-03-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, User> implements IUserService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public Boolean registry(User user) {
        return usersMapper.insert(user) == 1;
    }

    @Override
    public Boolean verify(User user) {
        return false;
    }
}
