package cn.edu.zzu.oj.controller.news;


import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.converter.WebEntityToFrontEntity;
import cn.edu.zzu.oj.dto.FileDTO;
import cn.edu.zzu.oj.entity.News;
import cn.edu.zzu.oj.entity.frontToWeb.NewFront;
import cn.edu.zzu.oj.service.impl.NewsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-03-31
 */

@BaseResponse
@RestController
@RequestMapping("/news")
public class NewsControllerTTT {
    private static Logger log = LoggerFactory.getLogger(NewsControllerTTT.class);

    @Autowired
    NewsServiceImpl newsService = null;

    //显示所有的新闻
    @GetMapping("/show")
    public List<NewFront> getAllNews(@RequestParam("pos") Integer pos, @RequestParam("limit") Integer limit){
        //暂时先不用redis
//        redisUtils.set("k", "v");

        return WebEntityToFrontEntity.NewsToNewsFront(newsService.getNews(pos, limit));
    }

    //查询新闻数目
    @GetMapping("/cnt")
    public Integer getNewsCnt(){
        return newsService.getNewsCnt();
    }

    @GetMapping("/get")
    public News getNewById(@RequestParam("id") Integer id){
        return newsService.getNewById(id);
    }

//    @PostMapping("/test1")
//    public String test1(@RequestParam("files") MultipartFile file
//    ) throws Exception {
//        return "123";
//    }
//
//    @PostMapping("/test2")
//    public String test2(@RequestBody  String file
//    ) throws Exception {
//        return "123";
//    }
//
//    @GetMapping("/test3")
//    public String test3(@RequestParam("file")  String file
//    ) throws Exception {
//        return "123";
//    }
}
