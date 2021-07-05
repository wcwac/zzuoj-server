package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.SourceCode;
import cn.edu.zzu.oj.entity.SourceT;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.parsing.Problem;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-06-01
 */
public interface ISourceCodeService extends IService<SourceCode> {
    public int insert(SourceCode sourceCode);

    //通过problemId查询

    //通过userId查询

    //通过problemId，userId查询
//    public List<> getProblemsPage(Integer pos, Integer limit) throws Exception
}
