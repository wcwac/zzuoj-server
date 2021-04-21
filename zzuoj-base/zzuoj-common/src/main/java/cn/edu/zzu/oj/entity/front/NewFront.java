package cn.edu.zzu.oj.entity.front;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class NewFront extends BaseFront{
    private Integer newsId;

    private String userId;

    //如果想拿nickname，暂时需要联表查询（user、problem),暂时先不修改这个数据。
    private String nickName;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    private Integer importance;

    private String defunct;
}
