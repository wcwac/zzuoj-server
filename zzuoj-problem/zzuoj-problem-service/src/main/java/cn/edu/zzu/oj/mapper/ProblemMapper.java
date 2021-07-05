package cn.edu.zzu.oj.mapper;

import cn.edu.zzu.oj.entity.Problem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-04-11
 */

@Repository
public interface ProblemMapper extends BaseMapper<Problem> {

    @Update("update problem set defunct = #{defunct} where problem_id =#{problemId}")
    Integer updateProblemDefunctStatus(Problem problem);

    @Update("update problem set submit = submit+1 where problem_id =#{problemId}")
    Integer increSubmit(Integer problemId);

    @Update("update problem set submit = submit+1, accepted = accepted + 1 where problem_id =#{problemId}")
    Integer increSubmitAC(Integer problemId);

}
