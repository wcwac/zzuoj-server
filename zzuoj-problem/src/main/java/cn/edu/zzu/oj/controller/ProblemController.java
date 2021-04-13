package cn.edu.zzu.oj.controller;


import cn.edu.zzu.oj.entity.Problem;
import cn.edu.zzu.oj.service.impl.ProblemServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yuliuyuan
 * @since 2021-04-11
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    ProblemServiceImpl problemService;

    //pos表示的是相对于第一条记录的偏移，数据库中第一条记录pos为0，
    @GetMapping("/show")
    public List<Problem> show(@RequestParam(value = "pos") Integer pos,
                              @RequestParam(value = "limit") Integer limit){
        Page<Problem> page = new Page<>(pos,limit);
        IPage<Problem> res = problemService.getProblemsPage(page);
        return res.getRecords();
    }

    @Transactional
    @PostMapping("/insert")
    public String insertProblems(@RequestParam(value = "problem_list") List<Problem> problems){
        Integer cnt = 0;
        try{
            problemService.insertProblems(problems);
        } catch (Exception e){
            //log在service层打
            return "insert error, transaction rollback!";
        }
        return "insert " + cnt + " problems success!";
    }
}
