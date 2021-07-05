package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.entity.SourceCode;
import cn.edu.zzu.oj.mapper.SourceCodeMapper;
import cn.edu.zzu.oj.service.ISourceCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-06-01
 */
@Service
public class SourceCodeServiceImpl extends ServiceImpl<SourceCodeMapper, SourceCode> implements ISourceCodeService {


    @Override
    public int insert(SourceCode sourceCode) {
        baseMapper.insert(sourceCode);
        return sourceCode.getSolutionId();
    }
}
