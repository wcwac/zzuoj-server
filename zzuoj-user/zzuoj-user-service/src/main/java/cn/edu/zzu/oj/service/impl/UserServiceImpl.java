package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.entity.User;
import cn.edu.zzu.oj.entity.webToFront.UserWeb;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.mapper.UserMapper;
import cn.edu.zzu.oj.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;

import java.util.List;

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
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

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
        log.info(uId);
        log.info(pwd);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", uId).eq("password", pwd);
        return userMapper.selectOne(queryWrapper);
    }

    public List<User> getUserPage(Integer pos, Integer limit) throws Exception {
        List<User> records = null;
        try {
            Page<User> page = new Page<>(pos,limit);
            IPage<User> userIPage = userMapper.selectPage(page, new QueryWrapper<User>());
            records = userIPage.getRecords();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return records;
    }

    @Override
    public Integer getUserCnt() throws Exception {
        Integer cnt = null;
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            cnt = userMapper.selectCount(queryWrapper);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return cnt;
    }

    @Override
    public Integer switchDefunctStatusByUid(String uid, String status) {
        Integer cnt = 0;
        try {
            cnt = userMapper.updateUserDefunctStatus(new User().setUserId(uid).setDefunct(status));
        } catch (Exception e){
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return cnt;
    }

}

