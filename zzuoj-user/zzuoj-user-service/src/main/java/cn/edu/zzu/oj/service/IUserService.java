package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-03-19
 */
public interface IUserService extends IService<User> {

    //注册
    public Boolean registry(User user);

    //验证
    public Boolean verify(User user);

    //登陆
    public User login(String uId, String pwd);
}
