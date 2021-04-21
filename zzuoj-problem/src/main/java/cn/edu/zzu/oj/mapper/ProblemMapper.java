package cn.edu.zzu.oj.mapper;

import cn.edu.zzu.oj.entity.Problem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    IPage<Problem> selectPageVo(Page<Problem> page);
}
