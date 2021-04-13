package cn.edu.zzu.oj.mapper;

import cn.edu.zzu.oj.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-03-31
 */
@Mapper
public interface NewsMapper extends BaseMapper<News> {

}
