package cn.edu.zzu.oj.entity.checkpoint;


import com.alibaba.fastjson.annotation.JSONField;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckPointConf {

    @JSONField(name = "test_cases")
    List<TestCaseDTO> testCaseList;

    @JSONField(name = "time_limit")
    Integer time_limit;

    @JSONField(name = "memory_limit")
    Integer memory_limit;

    @JSONField(name = "real_time_limit")
    Integer real_time_limit;

    @JSONField(name = "file_size_limit")
    Integer file_size_limit;

    @JSONField(name = "uid")
    Integer uid;

    @JSONField(name = "strict_mode")
    Boolean strict_mode;

}


//
//@Accessors(chain = true)
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//class SpecialJudge{
//    Integer mode;
//
//    String checker;
//
//    Boolean redirect_program_out;
//
//    Integer time_limit;
//
//    Integer memory_limit;
//
//    Boolean use_testlib;
//
//    checker_cases ;
//}