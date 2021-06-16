package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.omg.PortableInterceptor.INACTIVE;
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

    //分页获取用户
    public List<User> getUserPage(Integer pos, Integer limit) throws Exception;

    //获取用户个数
    public Integer getUserCnt() throws Exception;

    //是否改变禁用状态
    public Integer switchDefunctStatusByUid(String uid, String status);

    //通过userId获取user
    public User getUserById(String uId);

    public Integer updateUserByUserId(User user);

    public Integer increSubmitAc(String uid, boolean ac);
}
