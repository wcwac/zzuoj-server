package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.parsing.Problem;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-04
 */
public interface IUserService extends IService<User> {
    //注册
    public Boolean registry(User user);

    //验证
    public Boolean verify(User user);

    //登陆
    public User login(String uId, String pwd);

    public List<User> getUserPage(Integer pos, Integer limit) throws Exception;

    public Integer getUserCnt() throws Exception;

    public Integer switchDefunctStatusByUid(String uid, String status);
}
