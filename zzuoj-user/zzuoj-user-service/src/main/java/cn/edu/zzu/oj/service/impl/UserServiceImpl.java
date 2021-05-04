package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.entity.User;
import cn.edu.zzu.oj.mapper.UserMapper;
import cn.edu.zzu.oj.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean registry(User user) {
        return userMapper.insert(user) == 1;
    }

    @Override
    public Boolean verify(User user) {
        return false;
    }

    @Override
    public User login(String uId, String pwd) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", uId).eq("password", pwd);
        return userMapper.selectOne(queryWrapper);
    }
}
