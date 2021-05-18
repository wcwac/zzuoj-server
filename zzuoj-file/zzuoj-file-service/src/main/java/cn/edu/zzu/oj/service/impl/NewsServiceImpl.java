package cn.edu.zzu.oj.service.impl;

import cn.edu.zzu.oj.entity.News;
import cn.edu.zzu.oj.mapper.NewsMapper;
import cn.edu.zzu.oj.service.INewsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-03-31
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

    @Autowired
    NewsMapper newsMapper = null;

    @Override
    public int insert(News news) {
        return newsMapper.insert(news);
    }

    @Override
    public int deleteByNewsId(Serializable id) {
        return newsMapper.deleteById(id);
    }

    @Override
    public int updateByNewsId(News news) {
        return newsMapper.updateById(news);
    }

    @Override
    public List<News> getNews(Integer pos, Integer limit) {
        Page<News> page = new Page<>(pos, limit);
        Page<News> newsPage = newsMapper.selectPage(page, new QueryWrapper<>());
        return newsPage.getRecords();
    }

    @Override
    public News getNewById(Integer id) {
        return newsMapper.selectById(id);
    }

    @Override
    public Integer getNewsCnt() {
        return newsMapper.selectCount(new QueryWrapper<>());
    }
}
