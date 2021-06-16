package cn.edu.zzu.oj.mapper;

import cn.edu.zzu.oj.entity.Compileinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-06-02
 */

@Mapper
public interface CompileinfoMapper extends BaseMapper<Compileinfo> {

}
