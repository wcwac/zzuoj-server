package cn.edu.zzu.oj.service;

import cn.edu.zzu.oj.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-03-31
 */
public interface INewsService extends IService<News> {

    public int insert(News news);

    public int deleteByNewsId(Serializable id);

    public int updateByNewsId(News news);

    public List<News> getAllNews();
}
