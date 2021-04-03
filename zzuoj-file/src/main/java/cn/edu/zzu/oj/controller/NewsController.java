package cn.edu.zzu.oj.controller;


import cn.edu.zzu.oj.entity.News;
import cn.edu.zzu.oj.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-03-31
 */
@RestController
    @RequestMapping("/news")
public class NewsController {
    @Autowired
    NewsServiceImpl newsService = null;

    //参数注解等：https://blog.csdn.net/walkerJong/article/details/7946109?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-2.control&dist_request_id=1328761.1396.16171891489419989&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-2.control
    //@RequestParam 和 @PathVariable 注解是用于从request中接收请求的，两个都可以接收参数，关键点不同的是@RequestParam 是从request里面拿取值，而 @PathVariable 是从一个URI模板里面来填充
    //add news
    @PostMapping("/add/{news}")
    public String addNews(@RequestParam("news") News news){
        if(newsService.insert(news) == 1){
            return "add new success!";
        } else {
            return "add new fail!";
        }
    }


    @PostMapping("/update/{news}")
    public String updateNews(@RequestParam("news") News news){
        if(newsService.updateByNewsId(news) == 1){
            return "update new success!";
        } else {
            return "update new fail!";
        }
    }

    @PostMapping("/delete/{news}")
    public String deleteNews(@RequestParam("news") News news){
        if(newsService.deleteByNewsId(news.getNewsId()) == 1){
            return "update new success!";
        } else {
            return "update new fail!";
        }
    }

    //显示所有的新闻
    @GetMapping("/show")
    public List<News> getAllNews(){
        return newsService.getAllNews();
    }
}
