package cn.edu.zzu.oj.entity.checkpoint;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestCaseDTO{
    @JSONField(name = "handle")
    String handle;

    @JSONField(name = "name")
    String name;

    @JSONField(name = "input")
    String input;

    @JSONField(name = "output")
    String output;

    @JSONField(name = "enabled")
    Boolean enabled;
}
