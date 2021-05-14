package cn.edu.zzu.oj.mapper;

import cn.edu.zzu.oj.entity.ContestT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-05-13
 */
@Mapper
public interface ContestMapper extends BaseMapper<ContestT> {

    @Update("update contestT set defunct = #{defunct} where contest_id =#{contestId}")
    Integer updateContestDefunctStatus(ContestT contestT);
}
