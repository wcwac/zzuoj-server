package cn.edu.zzu.oj.controller.news;

import cn.edu.zzu.oj.Exceptions.BaseException;
import cn.edu.zzu.oj.anotation.BaseResponse;
import cn.edu.zzu.oj.entity.News;
import cn.edu.zzu.oj.enums.HttpStatus;
import cn.edu.zzu.oj.service.impl.NewsServiceImpl;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@BaseResponse
@RestController
@RequestMapping("/admin/news")
public class NewsAdminControllerTTT {
//    private static Logger log = LoggerFactory.getLogger(NewsAdminController.class);

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
            System.out.println(news.getContent());
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

}
