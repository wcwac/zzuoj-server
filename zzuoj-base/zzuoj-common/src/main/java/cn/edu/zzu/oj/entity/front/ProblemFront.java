package cn.edu.zzu.oj.entity.front;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemFront extends BaseFront {

    private Integer problemId;

    private String title;

    private String defunct;

    private Integer accepted;

    private Integer submit;
}
