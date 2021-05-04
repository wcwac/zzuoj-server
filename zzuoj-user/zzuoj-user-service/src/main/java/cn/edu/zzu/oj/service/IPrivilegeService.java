package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.Privilege;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-04
 */
public interface IPrivilegeService extends IService<Privilege> {

    //get
    public Integer getPrivilegeByUserId(String uId);

    //delete all privilege
    public Integer deletePrivilegeByUserId(String uId);

    //update
    public Integer updatePrivilegeByUserId(String uId, Integer n);

    //add
    public Integer addPrivilegeByUserId(String uId, Integer privilege);

}
