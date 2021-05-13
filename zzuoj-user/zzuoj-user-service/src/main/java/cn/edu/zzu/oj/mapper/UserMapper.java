package cn.edu.zzu.oj.mapper;

import cn.edu.zzu.oj.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.parsing.Problem;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-04
 */
public interface UserMapper extends BaseMapper<User> {
    @Update("update user set defunct = #{defunct} where user_id =#{userId}")
    Integer updateUserDefunctStatus(User user);

    @Update("update user set email = #{email}, nick = #{nick}, school = #{school} where user_id =#{userId}")
    Integer updateUserByUserId(User user);
}
