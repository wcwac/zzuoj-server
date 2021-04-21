package cn.edu.zzu.oj.controller;


import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.converter.WebEntityToFrontEntity;
import cn.edu.zzu.oj.entity.News;
import cn.edu.zzu.oj.entity.front.NewFront;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.NewsServiceImpl;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-03-31
 */

@Log4j2
@BaseResponse
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsServiceImpl newsService = null;

    //添加新闻
    @PostMapping("/add")
    public String addNews(@RequestBody String jsonData){
        Map<String, Object> params = null;
        try {
            params = JSON.parseObject(jsonData, HashMap.class);
            News news = new News()
                    .setContent((String) params.get("content"))
                    .setTitle((String) params.get("title"))
                    .setDefunct((String) params.get("defunct"))
                    .setImportance((Integer) params.get("importance"))
                    .setTime(new Date())
                    .setUserId("1");
            if(newsService.insert(news) == 1){
                return "add news success";
            }
        } catch (Exception e){
            log.error("add News error: " + e.toString());
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "add news fail";
    }

    //修改新闻
    @PostMapping("/update")
    public String updateNews(@RequestBody String jsonData){
        Map<String, Object> params = null;
        try {
            params = JSON.parseObject(jsonData, HashMap.class);
            News news = new News()
                    .setContent((String) params.get("content"))
                    .setTitle((String) params.get("title"))
                    .setDefunct((String) params.get("defunct"))
                    .setImportance((Integer) params.get("importance"))
                    .setNewsId((Integer) params.get("newsId"));
            if(newsService.updateByNewsId(news) == 1){
                return "update news success";
            }
        } catch (Exception e){
            log.error("updat News error: " + e.toString());
            throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return "update news fail";
    }

    //删除新闻
    @GetMapping("/delete")
    public String deleteNews(@RequestParam("id") Integer id){
        if(newsService.deleteByNewsId(id) == 1){
            return "delete news success!";
        } else {
            return "delete news fail!";
        }
    }

    //显示所有的新闻
    @GetMapping("/show")
    public List<NewFront> getAllNews(@RequestParam("pos") Integer pos, @RequestParam("limit") Integer limit){
        return WebEntityToFrontEntity.NewsToNewsFront(newsService.getNews(pos, limit));
    }

    @GetMapping("/get")
    public News getNewById(@RequestParam("id") Integer id){
        return newsService.getNewById(id);
    }

    //查询新闻数目
    @GetMapping("/cnt")
    public Integer getNewsCnt(){
        return newsService.getNewsCnt();
    }
}
